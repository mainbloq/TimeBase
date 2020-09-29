package deltix.qsrv.hf.pub.md;

import deltix.timebase.messages.*;
import deltix.util.io.IOUtil;
import deltix.util.lang.StringUtils;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 *  Extracts user-visible information from a native class.
 */
public class ClassAnnotator {
    public static final ClassAnnotator  DEFAULT = new ClassAnnotator ();

    public boolean          isAbstract (Class <?> cls) {
        return (Modifier.isAbstract (cls.getModifiers ()));
    }
    
    public String           getName (Class <?> cls) {
        return (ClassDescriptor.getClassNameWithAssembly (cls));
    }
    
    public String           getTitle (Class <?> cls) {
        SchemaElement title = cls.getAnnotation (SchemaElement.class);
        
        if (title != null)
            return (title.title ());
        
        return (getPrompt (cls, "this", cls.getName ()));
    }

    public String           getGuid(Class <?> cls) {
        SchemaGuid guid = cls.getAnnotation (SchemaGuid.class);
        if (guid != null)
            return guid.value();

        return null;
    }
    
    public boolean          isStatic(Field f) {
        return f.isAnnotationPresent (SchemaStaticType.class);
    }

    public boolean          isStatic(Method m) {
        return m.isAnnotationPresent (SchemaStaticType.class);
    }

    public String           getTitle (Field f) {
        SchemaElement           title = f.getAnnotation (SchemaElement.class);
        
        if (title != null)
            return (title.title ());
        
        return (getPrompt (f));
    }

    public String           getTitle (Method m, String field) {
        SchemaElement           title = m.getAnnotation (SchemaElement.class);

        if (title != null && !StringUtils.isEmpty(title.title()))
            return (title.title ());

        return (getPrompt (field, m));
    }
    
    public String           getDescription (Class <?> cls) {
        return (getPrompt (cls, "this.doc", ""));
    }

    public String        getDescription (Field field) {
        Class <?>   cls = field.getDeclaringClass ();
        String      fieldName = field.getName ();

        String  doc = getPrompt (cls, fieldName + ".doc", "");

        if (doc.isEmpty ()) {
            try {
                doc = IOUtil.readTextFromClassPath (
                                cls.getName ().replace ('.', '/') + '.' +
                                        fieldName + ".html"
                        );
            } catch (FileNotFoundException x) {
                // leave empty string alone
            } catch (Exception x) {
                doc = x.getMessage ();
            }
        }

        return (doc);
    }

    public String        getDescription (Method method, String fieldName) {
        Class <?>   cls = method.getDeclaringClass ();
//        String      fieldName = method.getAnnotation (SchemaElement.class).name();

        String  doc = getPrompt (cls, fieldName + ".doc", "");

        if (doc.isEmpty ()) {
            try {
                doc = IOUtil.readTextFromClassPath (
                        cls.getName ().replace ('.', '/') + '.' +
                                fieldName + ".html"
                );
            } catch (FileNotFoundException x) {
                // leave empty string alone
            } catch (Exception x) {
                doc = x.getMessage ();
            }
        }

        return (doc);
    }


    public static String        getPrompt (String key, Method method) {
        return (getPrompt (method.getDeclaringClass (), key, key));
    }

    public static String        getPrompt (Field field) {
        String  n = field.getName ();

        return (getPrompt (field.getDeclaringClass (), n, n));
    }

    public static String        getPrompt (Class <?> cls, String key, String def) {
        String      cname = cls.getName ();

        ClassLoader resourceClassLoader = cls.getClassLoader();

        try {
            String          rbname = cname + "Prompts";
            ResourceBundle rb = resourceClassLoader != null ?
                    ResourceBundle.getBundle (rbname, Locale.getDefault(), resourceClassLoader) :
                    ResourceBundle.getBundle (rbname);
            return (rb.getString (key));
        } catch (MissingResourceException x) {
            // fall through
        }

        int         dotPos = cname.lastIndexOf ('.');

        try {
            String          rbname = cname.substring (0, dotPos + 1) + "prompts";
            ResourceBundle  rb = resourceClassLoader != null ?
                    ResourceBundle.getBundle (rbname, Locale.getDefault(), resourceClassLoader) :
                    ResourceBundle.getBundle (rbname);

            return (rb.getString (cname.substring (dotPos + 1) + '.' + key));
        } catch (MissingResourceException x) {
            // fall through
        }

        return (def);
    }
}
