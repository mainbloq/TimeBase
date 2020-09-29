package deltix.qsrv.dtb.fs.chunkcache;

import deltix.qsrv.dtb.fs.local.LocalFS;
import deltix.qsrv.dtb.fs.pub.AbstractFileSystem;
import deltix.qsrv.dtb.fs.pub.AbstractPath;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Alexei Osipov
 */
public class ChunkCachingFileSystemLocalSpeedBenchmark {
    private static final boolean USE_CACHE = true;

    @Test
    public void run() throws Exception {
        LocalFS baseFs = new LocalFS();
        //Azure2FS baseFs = ChunkCacheUtils.getAzure2FS();

        long cacheSizeInBytes = 2 * 1024L * 1024 * 1024; // 2 Gb
        int maxFileSizeInBytes =  1 * 1024 * 1024 * 1024 + 1024; // 1 Gb + 1kb
        //int maxFileSizeInBytes =  Integer.MAX_VALUE / 2;
        //int testFileSizeInBytes =  1 * 1024 * 1024 * 1024 + 12; // 177 Mb + 12 bytes
        int testFileSizeInBytes =  177 * 1024 * 1024 + 12; // 177 Mb + 12 bytes
        //int testFileSizeInBytes =  Integer.MAX_VALUE / 2;


        AbstractFileSystem fs;
        if (USE_CACHE) {
            fs = new ChunkCachingFileSystem(baseFs, cacheSizeInBytes, maxFileSizeInBytes, 0);
            //fs = new CachingFileSystem(localFs, 1, maxFileSizeInBytes);
        } else {
            fs = baseFs;
        }

        AbstractPath testFolderPath = ChunkCacheTestUtils.createTestFolderPath(fs, baseFs);
        testFolderPath.makeFolderRecursive();
        AbstractPath filePath = ChunkCacheTestUtils.getOrCreateTestFile(testFolderPath, testFileSizeInBytes, false, baseFs);

        System.out.println("First read");
        testFileRead(filePath, testFileSizeInBytes);
        System.out.println("Second read");
        testFileRead(filePath, testFileSizeInBytes);
        System.out.println("Third read");
        testFileRead(filePath, testFileSizeInBytes);

        /*
        int n = 3;
        while (true) {
            n++;
            System.out.println("Read #: " + n);
            testFileRead(filePath, testFileSizeInBytes);
        }
        */

        //System.out.println(Arrays.asList(strings));
    }

    private void testFileRead(AbstractPath filePath, int testFileSizeInBytes) throws IOException {
        long t0 = System.nanoTime();
        ChunkCacheTestUtils.testFileReadByByteWithValidation(filePath, testFileSizeInBytes);

        long t1 = System.nanoTime();
        System.out.println("Total time: " + TimeUnit.NANOSECONDS.toMicros(t1 - t0) + " us");
        System.out.println("Read speed: " + (testFileSizeInBytes * TimeUnit.SECONDS.toNanos(1) / 1024 / 1024 / (t1 - t0)) + " Mb/s");
    }

}