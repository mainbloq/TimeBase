package deltix.qsrv.hf.stream;

import deltix.qsrv.hf.pub.md.RecordClassDescriptor;
import deltix.qsrv.hf.pub.md.ClassDescriptor;
import deltix.util.collections.Visitor;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.*;

/**
 * Internal class for  serialization set of different class descriptors
 */

@XmlRootElement(name = "ClassSet")
public class ClassSet implements deltix.qsrv.hf.pub.md.ClassSet<RecordClassDescriptor>, Serializable {

    @XmlElement (name = "classDescriptor")
    private HashSet <ClassDescriptor>               classDescriptors;

    @XmlElement (name = "contentClass")
    private List <String>                           contentClasses; // define class descriptors order

    public ClassSet() {
    }

    public ClassSet(RecordClassDescriptor[] classes) {
        defineClasses(classes);
    }

    public void                         addContentClasses(RecordClassDescriptor... cds) {
        defineClasses(cds);
    }    

    public RecordClassDescriptor[]      getContentClasses() {

        if (contentClasses == null)
            return null;

        List<RecordClassDescriptor>  result = new ArrayList<RecordClassDescriptor>(contentClasses.size());

        for (String guid : contentClasses) {
            for (ClassDescriptor descriptor : classDescriptors) {
                if (descriptor instanceof RecordClassDescriptor &&
                        guid.equals(descriptor.getGuid()) )
                    result.add((RecordClassDescriptor) descriptor);
            }
        }

        return result.toArray(new RecordClassDescriptor[result.size()]);
    }

    @Override
    public ClassDescriptor[]            getClasses() {
        return classDescriptors.toArray(new ClassDescriptor[classDescriptors.size()]);
    }

    private void                        defineClasses (RecordClassDescriptor[] cds) {

        if (contentClasses == null)
            contentClasses = new ArrayList <String> ();

        for (RecordClassDescriptor cd : cds) {
            if (!contentClasses.contains(cd.getGuid()))
                contentClasses.add (cd.getGuid());
        }

        final Queue<ClassDescriptor> addQueue = new ArrayDeque<ClassDescriptor>();

        for (ClassDescriptor cd : cds)
            addQueue.offer (cd);

        final Visitor<ClassDescriptor> adder =
            new Visitor <ClassDescriptor> () {                
                public boolean                      visit (ClassDescriptor cd) {
                    addQueue.offer (cd);
                    return (true);
                }
            };

        for (;;) {
            ClassDescriptor             cd = addQueue.poll ();

            if (cd == null)
                break;

            if (classDescriptors == null)
                classDescriptors = new HashSet<ClassDescriptor>();

            if (!classDescriptors.add (cd))
                continue;

            cd.visitDependencies (adder);
        }
    }

//    /*
//        Removes bar size from record class descriptors
//     */
//
//    Interval                    upgrade() {
//        Periodicity periodicity = null;
//
//        ClassDescriptor[] types = classDescriptors.toArray(new ClassDescriptor[classDescriptors.size()]);
//
//        for (ClassDescriptor cd : types) {
//            if (cd instanceof RecordClassDescriptor) {
//                RecordClassDescriptor rcd = TDBUpgrade23.removeBarSize((RecordClassDescriptor)cd);
//
//                if (rcd != null) {
//                    classDescriptors.remove(cd);
//                    classDescriptors.add(rcd);
//                    periodicity = TDBUpgrade23.getPeriodicity(((RecordClassDescriptor) cd).getField("barSize"));
//                }
//            }
//        }
//
//        return contentClasses.size() == 1 && periodicity != null ? periodicity.getInterval() : null;
//    }
}
