package deltix.qsrv.hf.security;

import javax.naming.NameAlreadyBoundException;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.ldap.LdapName;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import deltix.util.cmdline.DefaultApplication;
import deltix.util.lang.Depends;
import deltix.util.ldap.LDAPConnection;
import deltix.util.ldap.LDAPConnection.Vendor;
import deltix.util.ldap.config.Binding;
import deltix.util.ldap.config.Credentials;
import deltix.util.ldap.config.Query;
import deltix.util.ldap.security.Configuration;
import deltix.util.log.TerseFormatter;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@Depends({
    "config/uac-access-rules.xml",
    "config/uac-file-security.xml",
    "config/uac-ldap-security.xml"
})
public class SecurityConfigurator extends DefaultApplication {
    public static final String UAC_LOGGER_NAME = "deltix.uac";
    public static final Logger LOGGER = Logger.getLogger(UAC_LOGGER_NAME);
    public static final String LOGPREFIX = "[UAC] ";

    public final static String DEFAULT_DOMAIN   = "dc=deltix,dc=com";
    public final static String DEFAULT_USERNAME = "uid=admin,ou=system";

    public final static String DEFAULT_GROUPS_ENTRY_NAME = "groups";
    public final static String DEFAULT_USERS_ENTRY_NAME  = "users";

    public final static String DEFAULT_USERS_ENTRY  = "ou=" + DEFAULT_USERS_ENTRY_NAME + "," + DEFAULT_DOMAIN;

    public SecurityConfigurator(String[] args) {
        super(args);
    }

    public static void main(String[] args) throws Throwable {
        Handler[] handlers = Logger.getLogger("").getHandlers();
        Formatter formatter = new TerseFormatter();
        for (Handler handler : handlers)
            handler.setFormatter(formatter);

        if (args.length > 0)
            new SecurityConfigurator(args).run();
        else {
            System.out.println("Usage: uacconf -home <dir> -server <url> -user <username> -pass <password> [-domain <domain>]");
            System.out.println("\t<dir>               specifies QuantServer Home");
            System.out.println("\t<url>               specifies connection string to LDAP server,  for example ldap://localhost:10389");
            System.out.println("\t<username>          specifies LDAP server admin account");
            System.out.println("\t<password>          specifies password for the admin account");
            System.out.println("\t<domain>            specifies domain name for configuration, by default '" + DEFAULT_DOMAIN + "'");
            System.out.println();
            System.out.println("\tExample (ApacheDS): uacconf -home \"C:\\Deltix\\QuantServerHome\" -server ldap://localhost:10389 -user \"" + DEFAULT_USERNAME + "\" -pass secret");
        }
    }

    @Override
    protected void run() throws Throwable {
        if (!isArgSpecified("-server")) {
            System.out.println("Ldap URL is not specified. Use -url argument");
            return;
        }

        if (!isArgSpecified("-user")) {
            System.out.println("LDAP administrator account is not specified. Use -user argument");
            return;
        }

        if (!isArgSpecified("-home")) {
            System.out.println("QuantServer home is not specified. Use -home argument");
        }

        if (!isArgSpecified("-domain")) {
            System.out.println("Domain is not specified - this tool will use default domain '" + DEFAULT_DOMAIN + "'.");
        }

        String home = getMandatoryArgValue("-home");

        String domain = getArgValue("-domain", DEFAULT_DOMAIN);

        Configuration config = new Configuration();
        ArrayList<String> connections = new ArrayList<>();
        connections.add(getMandatoryArgValue("-server"));
        config.connection = connections;
        String user = getMandatoryArgValue("-user");
        String pass = getMandatoryArgValue("-pass");
        if (user != null)
            config.credentials = new Credentials(user, pass);

        DirContext ctx = LDAPConnection.connect(config.connection, user, config.credentials.get());

        Vendor vendor = getLdapVendor(ctx);
        LOGGER.info("[LDAP] Vendor: " + vendor);

        config.vendor = vendor;

        configureBinding(config, vendor);

        if (vendor == Vendor.ApacheDS) {
            LOGGER.info("[LDAP] Creating domain: " + domain + " ... ");
            createDomain(ctx, domain);

            // create users node
            LOGGER.info("[LDAP] Creating users node: " + DEFAULT_USERS_ENTRY_NAME + "," + domain + " ... ");
            createUnit(ctx, DEFAULT_USERS_ENTRY_NAME + "," + domain);

            // create groups node
            LOGGER.info("[LDAP] Creating groups node: " + DEFAULT_GROUPS_ENTRY_NAME + "," + domain + " ... ");
            createUnit(ctx, DEFAULT_GROUPS_ENTRY_NAME + "," + domain);

            config.groups = new ArrayList<>();
            config.groups.add(new Query("ou=" + DEFAULT_GROUPS_ENTRY_NAME + "," + domain, "(objectClass=groupOfUniqueNames)"));

            LOGGER.info("[LDAP] Done.");
        } else {
            config.groups = new ArrayList<>();
            config.groups.add(new Query("CN=Users," + domain, "(objectClass=group)"));
        }

        File file = new File(new File(home, "config"), "uac-ldap-security.xml");
        LOGGER.log(Level.INFO, "Saving UAC configuration to: " + file.getAbsolutePath());
        Configuration.write(config, file);

        System.out.println(" Done.");
    }

    private static Vendor getLdapVendor(DirContext context) throws NamingException, IllegalStateException {
        String snc = "schemaNamingContext"; // DSE attribute
        String objectClass = "objectClass";
        String vendorName = "vendorName";
        Attributes attr = context.getAttributes("", new String[] { snc, objectClass, vendorName });

        String vendor = String.valueOf(attr.get(vendorName));
        if (vendor != null && vendor.contains("Apache Software Foundation")) {
            return Vendor.ApacheDS;
        } else if (attr.get(snc) != null) { // Active Directory
            return Vendor.ActiveDirectory;
        } else {
            throw new IllegalStateException("LDAP vendor: " + context + " is not supported.");
        }
    }

    @SuppressFBWarnings(value = "LDAP_INJECTION",justification = "Sanitizing injectable parameters")
    public static void createDomain(DirContext ctx, String domain) throws NamingException {
        Attributes attrs = new BasicAttributes();

        Attribute classes = new BasicAttribute("objectClass");
        classes.add("top");
        classes.add("domain");
        attrs.put(classes);

        createSubContext(ctx, new LdapName( LDAPTools.escapeDN(domain)), attrs);
    }

    @SuppressFBWarnings(value = "LDAP_INJECTION",justification = "Sanitizing injectable parameters")
    public static void createUnit(DirContext ctx, String name) throws NamingException {
        Attributes container = new BasicAttributes();
        Attribute classes = new BasicAttribute("objectClass");
        classes.add("top");
        classes.add("organizationalUnit");
        container.put(classes);
        createSubContext(ctx, new LdapName("ou=" + LDAPTools.escapeDN(name)), container);
    }

    public static DirContext create(DirContext ctx, String name, String[] objectClass) throws NamingException {
        Attributes container = new BasicAttributes();

        Attribute classes = new BasicAttribute("objectClass");
        for (String c : objectClass)
            classes.add(c);

        container.put(classes);
        return ctx.createSubcontext(name, container);
    }


    public static void createSubContext(DirContext context, LdapName unitName, Attributes entryAttrs) {
        try {
            context.createSubcontext(unitName, entryAttrs);
        } catch (NameAlreadyBoundException e) {
            LOGGER.log(Level.INFO, "[LDAP] Schema attribute [{0}] already exists", e.getRemainingName());
        } catch (NamingException x) {
            LOGGER.log(Level.INFO, "[LDAP] Scheme attribute ({0}) [{1}] creation fail (maybe exists)", new Object[] {entryAttrs.get("m-name"), x.getRemainingName()});
        }
    }

    public static Configuration configureBinding(Configuration config, Vendor vendor) {
        if (vendor == Vendor.ApacheDS) {
            Binding user = config.user = new Binding();
            user.attributes.add(new deltix.util.ldap.config.Attribute("cn", "id", null));
            user.attributes.add(new deltix.util.ldap.config.Attribute("cn", null, "setName"));
            user.attributes.add(new deltix.util.ldap.config.Attribute("description", null, "setDescription"));
            user.attributes.add(new deltix.util.ldap.config.Attribute("entryDN", "distinguishedName", null));
            user.objectsClasses.add("person");
            user.objectsClasses.add("organizationalPerson");
            user.objectsClasses.add("inetOrgPerson");

            Binding group = config.group = new Binding();
            group.attributes.add(new deltix.util.ldap.config.Attribute("cn", "id", null));
            group.attributes.add(new deltix.util.ldap.config.Attribute("cn", null, "setName"));
            group.attributes.add(new deltix.util.ldap.config.Attribute("description", null, "setDescription"));
            group.attributes.add(new deltix.util.ldap.config.Attribute("entryDN", "distinguishedName", null));
            group.attributes.add(new deltix.util.ldap.config.Attribute("uniqueMember", null, "setMembers"));
            group.objectsClasses.add("groupOfUniqueNames");

        } else if (vendor == Vendor.ActiveDirectory) {
            Binding user = config.user = new Binding();
            user.attributes.add(new deltix.util.ldap.config.Attribute("sAMAccountName", "id", null));
            user.attributes.add(new deltix.util.ldap.config.Attribute("name", null, "setName"));
            user.attributes.add(new deltix.util.ldap.config.Attribute("description", null, "setDescription"));
            user.attributes.add(new deltix.util.ldap.config.Attribute("distinguishedName", "distinguishedName", null));
            user.objectsClasses.add("person");
            user.objectsClasses.add("user");
            user.objectsClasses.add("organizationalPerson");

            Binding group = config.group = new Binding();
            group.attributes.add(new deltix.util.ldap.config.Attribute("sAMAccountName", "id", null));
            group.attributes.add(new deltix.util.ldap.config.Attribute("name", null, "setName"));
            group.attributes.add(new deltix.util.ldap.config.Attribute("description", null, "setDescription"));
            group.attributes.add(new deltix.util.ldap.config.Attribute("distinguishedName", "distinguishedName", null));
            group.attributes.add(new deltix.util.ldap.config.Attribute("member", null, "setMembers"));
            group.objectsClasses.add("group");
        }

        return config;
    }

    public static boolean createADSRoot(DirContext context) throws NamingException {
        Attributes attributes = new BasicAttributes();
        attributes.put("objectClass","metaSchema");

        Attribute dependencies = new BasicAttribute("m-dependencies");
        dependencies.add("system");
        dependencies.add("core");
        attributes.put(dependencies);
        //try to create root node
        try {
            LdapName name = new LdapName("cn=deltix, ou=schema ");
            context.createSubcontext(name,attributes);
            LOGGER.log(Level.INFO, "[LDAP] Deltix schema not found.Creating schema");
            return true;
        } catch (NameAlreadyBoundException e) {
            //LOGGER.log(Level.INFO, "[LDAP] Schema already exists");
            return false;
        }
    }
}
