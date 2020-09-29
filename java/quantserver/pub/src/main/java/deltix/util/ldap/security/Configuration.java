package deltix.util.ldap.security;

import deltix.gflog.Log;
import deltix.gflog.LogFactory;
import deltix.util.lang.Depends;
import deltix.util.lang.StringUtils;
import deltix.util.ldap.LDAPConnection.Vendor;
import deltix.util.ldap.config.Binding;
import deltix.util.ldap.config.Credentials;
import deltix.util.ldap.config.Query;
import deltix.util.xml.JAXBContextFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;

@Depends({"../jaxb.index", "../../config/jaxb.index"})
@XmlRootElement(name = "config")
public class Configuration {
    private static final Log LOG = LogFactory.getLog(Configuration.class);
    public static JAXBContext CTX;
    static {
        try {
            String path = StringUtils.join(":", Configuration.class.getPackage().getName(), Query.class.getPackage().getName());
            CTX = JAXBContextFactory.newInstance(path);
        } catch (JAXBException x) {
            LOG.error("Failed to initialize JAXB context for security configuration: %s").with(x);
            throw new ExceptionInInitializerError(x);
        }
    }

    public Configuration() {
    } // JAXB

    @XmlElement(name = "vendor")
    public Vendor vendor = Vendor.ApacheDS;

    @XmlElement(name = "connection", required = true)
    public ArrayList<String> connection;

    @XmlElement(name = "credentials")
    public Credentials credentials;

    @XmlElement(name = "groups", required = true)
    public ArrayList<Query> groups;

    @XmlElement(name = "user", required = true)
    public Binding user;

    @XmlElement(name = "group", required = true)
    public Binding group;

    public static Configuration read(File file) throws JAXBException {
        Unmarshaller unmarshaller = JAXBContextFactory.createStdUnmarshaller(CTX);
        return (Configuration) unmarshaller.unmarshal(file);
    }

    public static void write(Configuration config, File file) throws JAXBException {
        Marshaller marshaller = JAXBContextFactory.createStdMarshaller(CTX);
        marshaller.marshal(config, file);
    }
}