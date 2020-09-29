package deltix.qsrv.solgen.java;

import deltix.qsrv.solgen.SolgenUtils;
import deltix.qsrv.solgen.base.Project;
import deltix.qsrv.solgen.base.Property;
import deltix.qsrv.solgen.base.PropertyFactory;

import javax.annotation.Nullable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class GradleProject implements Project {

    public static final String PROJECT_TYPE = "Gradle";

    public static final Property JAVA_PROJECT_ROOT = PropertyFactory.create(
            "java.project.root",
            "Directory, where project will be stored",
            false,
            SolgenUtils::isValidPath,
            SolgenUtils.getDefaultSamplesDirectory().resolve("java").toAbsolutePath().toString()
    );
    public static final Property JAVA_PROJECT_GROUP = PropertyFactory.create(
            "java.project.group",
            "Project group, like com.google.collections",
            false,
            GradleProject::isValidGroup,
            "org.sample"
    );
    public static final Property JAVA_PROJECT_NAME = PropertyFactory.create(
            "java.project.name",
            "Project name, like google-collections",
            false,
            GradleProject::isValidName,
            "sample-project"
    );

    public static final List<Property> PROPERTIES = Collections.unmodifiableList(Arrays.asList(
            JAVA_PROJECT_ROOT, JAVA_PROJECT_GROUP, JAVA_PROJECT_NAME
    ));

    private static final String GRADLE_ARCHIVE = "gradle-project-skeleton.zip";
    private static final String BUILD_GRADLE_TEMPLATE = "build.gradle-template";
    private static final String SETTINGS_GRADLE_TEMPLATE = "settings.gradle-template";
    private static final String README_TEMPLATE = "README.md-template";

    // build.gradle template params
    private static final String PROJECT_GROUP = "PROJECT_GROUP";

    // settings.gradle template params
    private static final String PROJECT_NAME = "PROJECT_NAME";

    // gradle.properties
    private static final String REPOSITORY_PROP = "deltix.repository";
    private static final String REPOSITORY_NAME_PROP = "deltix.repository.name";
    private static final String TB_VERSION_PROP = "deltix.timebase.version";
    private static final String COMMONS_VERSION_PROP = "deltix.commons.version";
    private static final String VERSION_PROP = "version";

    private static final String DEFAULT_REPO = "https://nexus.deltixhub.com/repository";
    private static final String DEFAULT_REPO_NAME = "rtc-java-development";
    private static final String DEFAULT_TB_VERSION = GradleProject.class.getPackage().getImplementationVersion();
    private static final String DEFAULT_COMMONS_VERSION = "6.0.34";
    private static final String DEFAULT_VERSION = "1.0.0-SNAPSHOT";

    private final Path root;
    private final Path srcPath;
    private final Path resPath;
    private final String group;
    private final String name;
    private final Map<String, String> templateParams = new HashMap<>();

    private final List<Path> scripts = new ArrayList<>();

    private final Properties properties = new Properties();

    public GradleProject(Path root, String group, String name) {
        this.root = root;
        this.srcPath = root.resolve("src")
                .resolve("main")
                .resolve("java");
        this.resPath = root.resolve("src")
                .resolve("main")
                .resolve("resources");
        this.group = group;
        this.name = name;

        templateParams.put(PROJECT_NAME, this.name);
        templateParams.put(PROJECT_GROUP, this.group);

        scripts.add(root.resolve("gradlew.bat"));
        scripts.add(root.resolve("gradlew"));

        properties.setProperty(REPOSITORY_PROP, DEFAULT_REPO);
        properties.setProperty(REPOSITORY_NAME_PROP, DEFAULT_REPO_NAME);
        properties.setProperty(TB_VERSION_PROP, DEFAULT_TB_VERSION == null ? "6.0.1": DEFAULT_TB_VERSION);
        properties.setProperty(COMMONS_VERSION_PROP, DEFAULT_COMMONS_VERSION);
        properties.setProperty(VERSION_PROP, DEFAULT_VERSION);
    }

    public GradleProject(Properties properties) {
        this(Paths.get(properties.getProperty(JAVA_PROJECT_ROOT.getName())),
                properties.getProperty(JAVA_PROJECT_GROUP.getName()),
                properties.getProperty(JAVA_PROJECT_NAME.getName()));
    }

    @Override
    public Path getSourcesRoot() {
        return srcPath;
    }

    @Override
    public Path getResourcesRoot() {
        return resPath;
    }

    @Override
    public Path getProjectRoot() {
        return root;
    }

    @Override
    public Path getLibsRoot() {
        return null;
    }

    @Override
    public List<Path> getScripts() {
        return scripts;
    }

    @Override
    public void markAsScript(Path path) {
        scripts.add(path);
    }

    @Override
    public void setProjectProperty(String key, String value) {
        properties.setProperty(key, value);
    }

    @Override
    public void createSkeleton() throws IOException {
        SolgenUtils.unzip(this.getClass().getPackage(), GRADLE_ARCHIVE, root);
        createBuildGradle();
        createSettingsGradle();
        createReadme();
    }

    @Override
    public void flush() throws IOException {
        properties.store(new PrintWriter(root.resolve("gradle.properties").toFile()), "");
    }

    public void setVersion(String version) {
        setProjectProperty(VERSION_PROP, version);
    }

    public void setTbVersion(String version) {
        setProjectProperty(TB_VERSION_PROP, version);
    }

    public void setRepository(String repository) {
        setProjectProperty(REPOSITORY_PROP, repository);
    }

    public void createBuildGradle() throws FileNotFoundException {
        String buildScript = SolgenUtils.readTemplateFromClassPath(this.getClass().getPackage(), BUILD_GRADLE_TEMPLATE, templateParams);
        try (PrintWriter writer = new PrintWriter(root.resolve("build.gradle").toFile())) {
            writer.print(buildScript);
        }
    }

    public void createSettingsGradle() throws FileNotFoundException {
        String buildScript = SolgenUtils.readTemplateFromClassPath(this.getClass().getPackage(), SETTINGS_GRADLE_TEMPLATE, templateParams);
        try (PrintWriter writer = new PrintWriter(root.resolve("settings.gradle").toFile())) {
            writer.print(buildScript);
        }
    }

    public void createReadme() throws FileNotFoundException {
        String buildScript = SolgenUtils.readTemplateFromClassPath(this.getClass().getPackage(), README_TEMPLATE, templateParams);
        try (PrintWriter writer = new PrintWriter(root.resolve("README.md").toFile())) {
            writer.print(buildScript);
        }
    }

    public String getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public static boolean isValidGroup(@Nullable String s) {
        return s != null && s.matches("^[a-z][a-z0-9]*(\\.[a-z][a-z0-9]*)*$");
    }

    public static boolean isValidName(@Nullable String s) {
        return s != null && s.matches("^[a-z][a-z0-9]*(-[a-z][a-z0-9]*)*$");
    }

}
