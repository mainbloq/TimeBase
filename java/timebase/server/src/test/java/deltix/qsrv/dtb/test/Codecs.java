package deltix.qsrv.dtb.test;

import deltix.qsrv.dtb.store.codecs.*;
import deltix.util.collections.generated.*;
import deltix.util.io.*;
import deltix.util.memory.*;
import java.io.*;
import org.junit.*;

import static org.junit.Assert.*;

/**
 *
 */
public class Codecs {
    public static void  compressDecompress () throws IOException {
        String              headerText = "Hello world";
        byte []             header = headerText.getBytes ();
        byte []             data = 
            IOUtil.readBytes (
                Home.getFile ("src/" + BasicIOUtil.class.getName ().replace ('.', '/') + ".java")
            );
        
        int                 n = data.length;
        
        System.out.println ("Uncompressed length: " + n);
        
        BlockCompressor comp = new LZ4BlockCompressor(1, new ByteArrayList());
        ByteArrayList       app = new ByteArrayList ();
        
        for (byte b : header)
            app.add (b);
        
        int                 defLength = comp.deflate (data, 0, n, app);
        
        System.out.println ("Compressed length: " + defLength);
        
        Assert.assertEquals (defLength + header.length, app.size ());
        
        byte []             deflated = app.getInternalBuffer ();
        
        String              checkHead = new String (deflated, 0, header.length);
        
        Assert.assertEquals (headerText, checkHead);
        
        
        
        BlockDecompressor decomp = new LZ4BlockDecompressor();
        
        byte []             inflated = new byte [n];
        
        decomp.inflate (deflated, header.length, defLength, inflated, 0, n);
        
        Assert.assertArrayEquals (data, inflated);        
    }
    
    private static void    testMessageSizeCodec (int v, int expSize) {
        assertEquals (
            "For value " + v + ": requiredFieldSize()",
            expSize,
            SymmetricSizeCodec.requiredFieldSize (v)
        );
        
        MemoryDataOutput            out = new MemoryDataOutput (1);

        SymmetricSizeCodec.write (v, out);

        assertEquals (
            "For value " + v + ": MemoryDataOutput.getSize()",
            expSize, 
            out.getSize ()
        );        

        byte []                     b = out.getBuffer ();

        assertEquals (
            "For value " + v + ": endByteToFieldSize(FIRST)", 
            expSize, 
            SymmetricSizeCodec.endByteToFieldSize (b [0])
        );
        
        assertEquals (
            "For value " + v + ": endByteToFieldSize(LAST)", 
            expSize, 
            SymmetricSizeCodec.endByteToFieldSize (b [expSize - 1])
        );
        
        int                         check = SymmetricSizeCodec.read (b, 0);

        assertEquals ("For value " + v + ": readForward()", v, check);                 
    }

    public static void     testMessageSizeCodec () {
        testMessageSizeCodec (0, 1);
        testMessageSizeCodec (1, 1);
        
        testMessageSizeCodec ((1 << 7) - 1, 1);
        testMessageSizeCodec (1 << 7, 2);
        testMessageSizeCodec ((1 << 7) + 1, 2);
        
        testMessageSizeCodec ((1 << 12) - 1, 2);
        testMessageSizeCodec (1 << 12, 3);
        testMessageSizeCodec ((1 << 12) + 1, 3);
        
        testMessageSizeCodec ((1 << 18) - 1, 3);
        testMessageSizeCodec (1 << 18, 4);
        testMessageSizeCodec ((1 << 18) + 1, 4);

        testMessageSizeCodec ((1 << 26) - 1, 4);
        
        
        
//        out.reset ();
//        try {
//            MessageSizeCodec.write (0x400000, out);
//            assertTrue (false);
//        } catch (IllegalArgumentException x) {
//        }
//
//        try {
//            MessageSizeCodec.write (-1, out);
//            assertTrue (false);
//        } catch (IllegalArgumentException x) {
//        }
        
    }
    
    public static void      main (String [] args) throws Exception {
        testMessageSizeCodec ();
        
        System.out.println ("SUCCESS");
    }
}
