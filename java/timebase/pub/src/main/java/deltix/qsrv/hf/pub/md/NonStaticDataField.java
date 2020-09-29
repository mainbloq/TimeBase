package deltix.qsrv.hf.pub.md;

import deltix.qsrv.hf.pub.md.ClassDescriptor.TypeResolver;
import deltix.timebase.messages.PrimaryKey;
import deltix.timebase.messages.RelativeTo;

import javax.xml.bind.annotation.XmlElement;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Hashtable;

import static deltix.qsrv.hf.pub.util.SerializationUtils.readNullableString;
import static deltix.qsrv.hf.pub.util.SerializationUtils.writeNullableString;


public final class NonStaticDataField extends DataField {
	private static final long serialVersionUID = 2L;
	
    @XmlElement (name = "pk")
    private boolean           pk;

    @XmlElement (name = "relativeTo")
    private String            relativeTo;

    /**
     *  JAXB constructor
     */
    NonStaticDataField () {
        super ();
        pk = false;
        relativeTo = null;
    }

    /**
     *  Constructs a regular NonStaticDataField,
     *  that is not a primary key component and not placed relative to
     *  another field.
     */
    public NonStaticDataField (
        String          name,         
        String          title,
        DataType        type
    )
    {        
        this (name, title, type, false, null);
    }
    
    /**
     *  Constructs a DataField that is not a primary key component.
     */
    public NonStaticDataField (
        String          name,         
        String          title,
        DataType        type,
        String          relativeTo
    )
    {        
        this (name, title, type, false, relativeTo);
    }
    
    /**
     *  Constructs a fully initialized NonStaticDataField.
     */
    public NonStaticDataField (
        String          name,
        String          title,
        DataType        type, 
        boolean         pk, 
        String          relativeTo
    )
    {
        this (name, title, type, pk, relativeTo, false);
    }

    public NonStaticDataField (
            String          inName,
            String          inTitle,
            DataType        type,
            boolean         pk,
            String          relativeTo,
            boolean         displayIdentifier
    )
    {
        super (inName, inTitle, type);
        this.pk = pk;
        this.relativeTo = relativeTo;

        if (displayIdentifier) {
            if (!(type instanceof VarcharDataType) && !(type instanceof IntegerDataType))
                throw new IllegalArgumentException("DataType " + type + " is not supported id identifier");
        }

    }

    public NonStaticDataField (NonStaticDataField template, DataType newType) {
        super (template, newType);
        pk = template.pk;
        relativeTo = template.relativeTo;
    }

    NonStaticDataField (Field f, ClassAnnotator annotator, DataType inType) {
        super (f, annotator, inType);
        
        pk = f.isAnnotationPresent (PrimaryKey.class);
        
        final RelativeTo relativeAnnotation =
            f.getAnnotation (RelativeTo.class);
        
        relativeTo = 
            relativeAnnotation == null ?
                null :
                relativeAnnotation.value ();
    }

    NonStaticDataField (String inName, Method m, ClassAnnotator annotator, DataType inType) {
        super (inName, m, annotator, inType);

        pk = m.isAnnotationPresent (PrimaryKey.class);

        final RelativeTo relativeAnnotation =
                m.getAnnotation (RelativeTo.class);

        relativeTo =
                relativeAnnotation == null ?
                        null :
                        relativeAnnotation.value ();
    }


    public boolean              isPk () {
        return pk;
    }
    
    public String               getRelativeTo () {
        return relativeTo;
    }

    @Override
    public void                 writeTo (DataOutputStream out, int serial)
        throws IOException
    {
        out.writeByte (T_NON_STATIC_FIELD);

        super.writeTo (out, serial);

        out.writeBoolean (pk);
        writeNullableString (relativeTo, out);
        if (serial == 1)
            out.writeBoolean (false);
    }

    @Override
    protected void      readFields (
        DataInputStream     in,
        TypeResolver        resolver,
        int                 serial
    )
        throws IOException
    {
        super.readFields (in, resolver, serial);

        pk = in.readBoolean ();
        relativeTo = readNullableString (in);
        if (serial == 1)
            in.readBoolean();
    }

    @Override
    public void         setAttributes(Hashtable<String, String> attrs) {
    }

    @Override
    public Hashtable<String, String> getAttributes() {
        return new Hashtable<String, String>();
    }
}
