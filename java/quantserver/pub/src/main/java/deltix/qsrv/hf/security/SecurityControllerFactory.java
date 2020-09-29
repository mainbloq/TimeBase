package deltix.qsrv.hf.security;

import deltix.qsrv.QSHome;
import deltix.qsrv.hf.security.rules.xml.FileBasedAccessControlProvider;
import deltix.qsrv.hf.security.simple.SimpleUserDirectoryFactory;
import deltix.util.io.UncheckedIOException;
import deltix.util.lang.Factory;
import deltix.util.ldap.security.LdapUserDirectoryFactory;
import deltix.util.security.AccessControlRulesFactory;
import deltix.util.security.AuthenticatingUserDirectory;
import deltix.util.time.Interval;

import java.io.File;
import java.util.logging.Logger;

public class SecurityControllerFactory {
    protected static final Logger LOGGER = Logger.getLogger ("deltix.util.tomcat");
    public static final String LDAP_SECURITY_FILE_NAME = "uac-ldap-security.xml";
    public static final String SIMPLE_SECURITY_FILE_NAME = "uac-file-security.xml";
    public static final String ACCESS_RULES_FILE_NAME = "uac-access-rules.xml";

    public static DefaultSecurityController createLDAP(Interval updateInterval) throws Exception {
        File configFile = getConfigFile(LDAP_SECURITY_FILE_NAME);
        LOGGER.info("[UAC] Initializing LDAP security from file: " + configFile.getAbsolutePath());
        Factory<AuthenticatingUserDirectory> userDirectory = new LdapUserDirectoryFactory(configFile);
        return getDefaultSecurityController(updateInterval, userDirectory);
    }

    public static DefaultSecurityController createFile(Interval updateInterval) throws Exception {
        File configFile = getConfigFile(SIMPLE_SECURITY_FILE_NAME);
        LOGGER.info("[UAC] Initializing FILE security from file: " + configFile.getAbsolutePath());
        Factory<AuthenticatingUserDirectory> userDirectory = new SimpleUserDirectoryFactory(configFile);
        return getDefaultSecurityController(updateInterval, userDirectory);
    }

    private static File getConfigFile(String fileName) {
        File configFolder = QSHome.getFile("config");
        File configFile = new File(configFolder, fileName);
        if ( ! configFile.exists())
            throw new UncheckedIOException("Security configuration file is not found: " + configFile.getAbsolutePath());
        return configFile;
    }

    private static DefaultSecurityController getDefaultSecurityController(Interval updateInterval, Factory<AuthenticatingUserDirectory> userDirectory) {
        File rulesFile = new File (QSHome.getFile("config"), ACCESS_RULES_FILE_NAME);
        if ( ! rulesFile.exists())
            throw new UncheckedIOException("Access Rules configuration file is not found: " + rulesFile.getAbsolutePath());
        AccessControlRulesFactory rulesFactory = new FileBasedAccessControlProvider(rulesFile);
        return new DefaultSecurityController(userDirectory, rulesFactory, updateInterval);
    }

}
