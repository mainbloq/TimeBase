package deltix.qsrv.hf.tickdb.qql;

import deltix.qsrv.hf.pub.ChannelQualityOfService;
import deltix.qsrv.hf.tickdb.pub.*;
import deltix.qsrv.hf.tickdb.testframework.*;
import deltix.util.io.Home;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;

import org.junit.experimental.categories.Category;
import deltix.util.JUnitCategories.TickDBQQL;

/**
 * 
 */
@Category(TickDBQQL.class)
public class Test_AllTypes {
    private static final String     PATH = "temp/test/qdb";
    
    private static DXTickDB    db;

    @BeforeClass
    public static void      start () throws Throwable {
        File        f = Home.getFile (PATH);
        
        f.mkdirs ();
        
        db = TickDBFactory.create (f);
                
        db.format ();
    }

    @AfterClass
    public static void      stop () throws Throwable {
        db.close ();
    }

    public void             createAndCheckAllTypesStream (ChannelQualityOfService qos) {
        TestAllTypesStreamCreator   c = new TestAllTypesStreamCreator (db);

        c.createStream ();        
        c.loadTestData (qos);
        c.verifyTestData (qos); 
    } 

    @Test
    public void             createAndCheckCompiled () {
        createAndCheckAllTypesStream (ChannelQualityOfService.MAX_THROUGHPUT);
    }

    @Test
    public void             createAndCheckIntp () {
        createAndCheckAllTypesStream (ChannelQualityOfService.MIN_INIT_TIME);
    }    
}
