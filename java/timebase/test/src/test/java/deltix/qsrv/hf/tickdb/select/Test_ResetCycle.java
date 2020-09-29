package deltix.qsrv.hf.tickdb.select;

import deltix.qsrv.hf.tickdb.*;
import deltix.qsrv.hf.tickdb.pub.*;

import static deltix.qsrv.testsetup.TickDBCreator.*;
import org.junit.*;

import org.junit.experimental.categories.Category;
import deltix.util.JUnitCategories.TickDBFast;

/**
 *  Test correctness of the output when the cursor is repeatedly reset.
 *  This exercises the accumulator component and tests the correctness of
 *  state transitions associated with accumulation.
 */
@Category(TickDBFast.class)
public class Test_ResetCycle {
    private DXTickDB                db;
    private final String            LOCATION = TDBRunner.getTemporaryLocation();

    @Before
    public final void           startup() throws Throwable {
        db = openStdTicksTestDB (LOCATION);
    }

    @After
    public final void           teardown () {
        db.close ();
    }

    private class ResetCycleTester extends TickDBTest {
        private final boolean       raw;

        public ResetCycleTester (boolean raw) {
            this.raw = raw;
        }

        @Override
        public void                 run (DXTickDB db) throws Exception {
            CursorTester    ct = new CursorTester (db, new SelectionOptions (raw, false));
                        
            try {
                for (int ii = 0; ii < 10; ii++) {
                    ct.reset (0, 0, 0);
                    ct.addEntities (2); // S1
                    ct.addTypes (2);    // FloatMessage only
                    ct.addStreams (7);  // all three
                    
                    for (int jj = 0; jj < 10; jj++) {
                        ct.checkOne (true);                        
                    }
                }
            } finally {
                ct.close ();
            }
        }
    }

    @Test
    public void             resetCycleLocalRaw () throws Exception {
        new ResetCycleTester (true).run (db);
    }

    @Test
    public void             resetCycleLocalNative () throws Exception {
        new ResetCycleTester (false).run (db);
    }

    @Test
    public void             resetCycleRemoteRaw () throws Exception {
        new ResetCycleTester (true).runRemote (db);
    }

    @Test
    public void             resetCycleRemoteNative () throws Exception {
        new ResetCycleTester (false).runRemote (db);
    }   
}
