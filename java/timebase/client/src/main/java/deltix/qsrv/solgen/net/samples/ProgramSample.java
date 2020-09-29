package deltix.qsrv.solgen.net.samples;

import deltix.qsrv.solgen.SolgenUtils;
import deltix.qsrv.solgen.base.Project;
import deltix.qsrv.solgen.base.Sample;
import deltix.qsrv.solgen.base.StringSource;
import deltix.qsrv.solgen.net.NetSampleFactory;

import java.util.*;

import static deltix.qsrv.solgen.net.NetSampleFactory.READ_STREAM;
import static deltix.qsrv.solgen.net.NetSampleFactory.WRITE_STREAM;

public class ProgramSample implements Sample {

    private final List<Sample> samples = new ArrayList<>();

    private final StringSource activity = new StringSource("Activity.cs",
            SolgenUtils.readFromClassPath(this.getClass().getPackage(), "Activity.cs"));
    private final StringSource introspect = new StringSource("IntrospectClass.cs",
            SolgenUtils.readFromClassPath(this.getClass().getPackage(), "IntrospectClass.cs"));
    private final StringSource typeMap = new StringSource("TypeMap.cs",
            SolgenUtils.readFromClassPath(this.getClass().getPackage(), "TypeMap.cs"));
    private final StringSource listStreams;
    private final StringSource program;

    private final String launchSettings;


    public ProgramSample(List<String> sampleTypes, Properties properties) {
        for (String sampleType : sampleTypes) {
            switch (sampleType) {
                case READ_STREAM:
                    samples.add(new ReadStreamSample(properties));
                    break;
                case WRITE_STREAM:
                    samples.add(new WriteStreamSample(properties));
                    break;
                default:
                    throw new UnsupportedOperationException();
            }
        }
        Map<String, String> params = new HashMap<>();
        params.put(NetSampleFactory.TB_URL.getName(), properties.getProperty(NetSampleFactory.TB_URL.getName()));

        listStreams = new StringSource("ListStreams.cs",
                SolgenUtils.readTemplateFromClassPath(this.getClass().getPackage(), "ListStreams.cs-template", params));
        program = new StringSource("Program.cs", getProgram(sampleTypes));
        launchSettings = getLaunchSettings(sampleTypes);
    }

    @Override
    public void addToProject(Project project) {
        samples.forEach(s -> s.addToProject(project));
        project.addSource(activity);
        project.addSource(introspect);
        project.addSource(typeMap);
        project.addSource(listStreams);
        project.addSource(program);
        project.addRoot("Properties", "launchSettings.json", launchSettings);
    }

    private static String getProgram(List<String> sampleTypes) {
        if (sampleTypes == null || sampleTypes.isEmpty())
            throw new UnsupportedOperationException();

        StringBuilder sb = new StringBuilder();
        for (String sampleType : sampleTypes) {
            switch (sampleType) {
                case READ_STREAM:
                    sb.append(READ_STREAM_BLOCK);
                    break;
                case WRITE_STREAM:
                    sb.append(WRITE_STREAM_BLOCK);
                    break;
                default:
                    throw new UnsupportedOperationException();
            }
        }
        Map<String, String> params = new HashMap<>();
        params.put("block", sb.toString());
        return SolgenUtils.readTemplateFromClassPath(ProgramSample.class.getPackage(), "Program.cs-template", params);
    }

    private static String getLaunchSettings(List<String> sampleTypes) {
        if (sampleTypes == null || sampleTypes.isEmpty())
            throw new UnsupportedOperationException();

        String arg = "ReadStream";
        for (String sampleType : sampleTypes) {
            if (WRITE_STREAM.equals(sampleType)) {
                arg = "WriteStream";
                break;
            }
        }

        Map<String, String> params = new HashMap<>();
        params.put("defaultRunArg", arg);

        return SolgenUtils.readTemplateFromClassPath(ProgramSample.class.getPackage(), "launchSettings.json-template", params);
    }

    private static final String READ_STREAM_BLOCK = "" +
            "            else if (args[0] == \"ReadStream\")\n" +
            "                ReadStream.Run();\n";
    private static final String WRITE_STREAM_BLOCK = "" +
            "            else if (args[0] == \"WriteStream\")\n" +
            "                WriteStream.Run();";
}
