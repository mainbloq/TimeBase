package deltix.qsrv.hf.tickdb.topic;

import deltix.qsrv.hf.pub.md.RecordClassDescriptor;
import deltix.qsrv.hf.tickdb.pub.topic.TopicDB;
import deltix.qsrv.hf.tickdb.pub.topic.settings.MulticastTopicSettings;
import deltix.qsrv.hf.tickdb.pub.topic.settings.TopicSettings;
import deltix.util.JUnitCategories;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * @author Alexei Osipov
 */
@Category(JUnitCategories.TickDB.class)
public class Test_TopicMulticastPoller extends Test_TopicPollerBase {
    @Test(timeout = TEST_TIMEOUT)
    @Ignore // Disabled because this tests sends real multicast UDP traffic and can disturb other applications in same network.
    public void test() throws Exception {
        executeTest();
    }

    @Override
    protected void createTopic(TopicDB topicDB, String topicKey, RecordClassDescriptor[] types) {
        MulticastTopicSettings settings = new MulticastTopicSettings();
        settings.setTtl(1);
        topicDB.createTopic(topicKey, types,  new TopicSettings().setMulticastSettings(settings));
    }
}