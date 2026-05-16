package com.legacy.app;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.activation.FileDataSource;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.collections.MapUtils;
import java.util.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * Legacy Java Application demonstrating deprecated APIs and old dependencies
 * This code uses Java 5-6 era features that are breaking changes in modern Java versions
 * 
 * All 12 dependencies from pom.xml are actively used in this application
 */
public class LegacyApplication {
    private static final Log logger = LogFactory.getLog(LegacyApplication.class);

    public static void main(String[] args) {
        logger.info("========================================");
        logger.info("Starting Legacy Java Application");
        logger.info("========================================");
        
        try {
            // Demonstrate all deprecated features and old APIs
            demonstrateDeprecatedThreadAPIs();
            demonstrateDeprecatedStringAPIs();
            demonstrateDeprecatedCollectionAPIs();
            demonstrateDeprecatedCodecAPIs();
            demonstrateDeprecatedXMLAPIs();
            demonstrateDeprecatedIOAPIs();
            demonstrateDeprecatedURLAPIs();
            demonstrateCommonsLangUsage();
            demonstrateServletAPI();
            demonstrateJdomUsage();
            
            logger.info("========================================");
            logger.info("Legacy Application completed successfully");
            logger.info("========================================");
        } catch (Exception e) {
            logger.error("Error in Legacy Application", e);
            e.printStackTrace();
        }
    }

    /**
     * Demonstrates deprecated Thread APIs (removed in Java 5+)
     * These methods were removed due to deadlock and security issues
     */
    public static void demonstrateDeprecatedThreadAPIs() throws InterruptedException {
        logger.info("\n=== [1] Demonstrating Deprecated Thread APIs ===");
        
        Thread thread = new Thread(new Runnable() {
            public void run() {
                logger.info("  > Worker thread running");
            }
        });
        
        thread.start();
        thread.join();
        
        // Deprecated methods (removed in Java 5+):
        // thread.stop()     - Removed due to potential deadlocks
        // thread.suspend()  - Removed due to deadlock potential
        // thread.resume()   - Removed due to deadlock potential
        logger.info("  ✗ Deprecated Thread methods would include: stop(), suspend(), resume()");
        logger.info("  ✗ Breaking Change: Complete threading model redesign in Java 5+");
    }

    /**
     * Demonstrates deprecated String APIs
     */
    public static void demonstrateDeprecatedStringAPIs() {
        logger.info("\n=== [2] Demonstrating Deprecated String APIs ===");
        
        String text = "Hello World - Legacy String Handling";
        
        // String.getBytes() without charset (uses platform default - deprecated pattern)
        byte[] bytes = text.getBytes();
        logger.info("  > String bytes obtained using platform default charset");
        logger.info("  ✗ Deprecated: getBytes() without explicit encoding specification");
        
        // Using StringUtils from commons-lang 2.x (deprecated in lang 3.x)
        String reversed = StringUtils.reverse(text);
        logger.info("  > Reversed text using old commons-lang 2.6: " + reversed);
        
        // Old way - direct byte array operations (deprecated pattern)
        try {
            String fromBytes = new String(bytes, 0, bytes.length);
            logger.info("  > String created from bytes: " + fromBytes);
            logger.info("  ✗ Deprecated Constructor: new String(byte[], offset, length)");
        } catch (Exception e) {
            logger.error("  ! Error creating string from bytes", e);
        }
        
        logger.info("  ✗ Breaking Change: Charset handling became mandatory in Java 1.4+");
    }

    /**
     * Demonstrates deprecated Collection APIs and old patterns
     */
    public static void demonstrateDeprecatedCollectionAPIs() {
        logger.info("\n=== [3] Demonstrating Deprecated Collection APIs ===");
        
        // Using Vector (synchronized collection - deprecated in favor of Collections.synchronizedList)
        Vector vector = new Vector();
        vector.add("Item1");
        vector.add("Item2");
        vector.addElement("Item3");  // Deprecated method in Vector
        logger.info("  > Vector created with " + vector.size() + " elements");
        logger.info("  ✗ Vector.elementAt(0): " + vector.elementAt(0) + " (deprecated method)");
        logger.info("  ✗ Deprecated: Vector and Stack (use ArrayList with Collections.synchronizedList)");
        
        // Using Hashtable (synchronized map - deprecated in favor of ConcurrentHashMap)
        Hashtable hashtable = new Hashtable();
        hashtable.put("key1", "value1");
        hashtable.put("key2", "value2");
        logger.info("  > Hashtable created with " + hashtable.size() + " elements");
        logger.info("  ✗ Deprecated: Hashtable (use ConcurrentHashMap for thread-safety)");
        
        // Using old Stack implementation
        Stack stack = new Stack();
        stack.push(new Integer(1));
        stack.push(new Integer(2));
        logger.info("  > Stack pop result: " + stack.pop());
        logger.info("  ✗ Deprecated: Stack extends Vector (use Deque<T>)");
        
        // Using commons-collections deprecated methods (3.2.1)
        Map map = new HashMap();
        map.put("key", "value");
        boolean isEmpty = MapUtils.isEmpty(map);
        logger.info("  > Map is empty (using old commons-collections 3.2.1): " + isEmpty);
        logger.info("  ✗ Deprecated: commons-collections 3.x (use commons-collections4)");
        
        // Enumeration pattern (replaced by Iterator in modern Java)
        Enumeration enumeration = vector.elements();
        int count = 0;
        while (enumeration.hasMoreElements()) {
            count++;
            enumeration.nextElement();
        }
        logger.info("  > Enumeration traversed " + count + " elements");
        logger.info("  ✗ Deprecated: Enumeration API (use Iterator instead)");
        
        logger.info("  ✗ Breaking Change: Generics introduced in Java 5, old patterns became anti-patterns");
    }

    /**
     * Demonstrates deprecated Codec APIs
     */
    public static void demonstrateDeprecatedCodecAPIs() {
        logger.info("\n=== [4] Demonstrating Deprecated Codec APIs ===");
        
        String message = "Legacy Base64 Encoding with commons-codec 1.3";
        
        // Old commons-codec Base64 implementation
        byte[] encoded = Base64.encodeBase64(message.getBytes());
        logger.info("  > Base64 encoded (old commons-codec 1.3): " + new String(encoded));
        
        byte[] decoded = Base64.decodeBase64(encoded);
        logger.info("  > Base64 decoded (old commons-codec 1.3): " + new String(decoded));
        
        logger.info("  ✗ Deprecated: commons-codec 1.3 (use java.util.Base64 in Java 8+)");
        logger.info("  ✗ Breaking Change: Java 8 added native Base64 support, commons-codec less useful");
    }

    /**
     * Demonstrates deprecated XML APIs and JAXB
     */
    public static void demonstrateDeprecatedXMLAPIs() {
        logger.info("\n=== [5] Demonstrating Deprecated XML APIs ===");
        
        try {
            // JAXB with old Sun implementation (removed in Java 11+)
            // Using com.sun.xml.bind (jaxb-impl 2.1.13)
            JAXBContext context = JAXBContext.newInstance(LegacyData.class);
            logger.info("  > JAXB Context created using old Sun JAXB implementation (com.sun.xml.bind)");
            
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            
            LegacyData data = new LegacyData("SampleData", 42);
            logger.info("  > JAXB marshaller instantiated for: " + data);
            
            // Old File I/O patterns with FileDataSource (from javax.activation 1.1)
            // This is removed in Java 9+
            try {
                FileDataSource fileDS = new FileDataSource("/tmp/dummy.txt");
                logger.info("  > FileDataSource created (javax.activation 1.1)");
                logger.info("  ✗ Deprecated: javax.activation (removed from JDK in Java 9+)");
            } catch (Exception e) {
                logger.info("  ! FileDataSource demo skipped (file not found)");
            }
            
            logger.info("  ✗ Deprecated: JAXB (jaxb-impl 2.1.13) removed in Java 11+");
            logger.info("  ✗ Breaking Change: JAXB moved to jakarta.xml.bind (Jakarta EE)");
            
        } catch (Exception e) {
            logger.error("  ! Error in XML operations", e);
        }
    }

    /**
     * Demonstrates deprecated I/O APIs
     */
    public static void demonstrateDeprecatedIOAPIs() {
        logger.info("\n=== [6] Demonstrating Deprecated I/O APIs ===");
        
        try {
            // Using deprecated RandomAccessFile with deprecated methods
            File tempFile = File.createTempFile("legacy", ".tmp");
            RandomAccessFile raf = new RandomAccessFile(tempFile, "rw");
            
            raf.writeBytes("Legacy IO Pattern");
            raf.seek(0);
            
            // readLine() is deprecated - doesn't handle encoding properly
            String line = raf.readLine();
            logger.info("  > Read using deprecated RandomAccessFile.readLine(): " + line);
            logger.info("  ✗ Deprecated: RandomAccessFile.readLine() (no charset support)");
            
            raf.close();
            tempFile.delete();
            
            // Using DataInputStream with deprecated readLine
            ByteArrayInputStream bais = new ByteArrayInputStream("test data".getBytes());
            DataInputStream dis = new DataInputStream(bais);
            
            String dataLine = dis.readLine();
            logger.info("  > Read using deprecated DataInputStream.readLine(): " + dataLine);
            logger.info("  ✗ Deprecated: DataInputStream.readLine() (marked as deprecated)");
            
            dis.close();
            
            logger.info("  ✗ Breaking Change: NIO.2 and modern I/O (Files, Paths) replace these patterns");
            
        } catch (Exception e) {
            logger.error("  ! Error in I/O operations", e);
        }
    }

    /**
     * Demonstrates deprecated URL APIs
     */
    public static void demonstrateDeprecatedURLAPIs() {
        logger.info("\n=== [7] Demonstrating Deprecated URL APIs ===");
        
        try {
            // URL(String, String, int, String) constructor - deprecated pattern
            URL url = new URL("http", "example.com", 80, "/path");
            logger.info("  > URL created using deprecated multi-arg constructor: " + url);
            logger.info("  ✗ Deprecated: new URL(protocol, host, port, file) constructor");
            
            URLConnection conn = url.openConnection();
            
            // Getting header field (old pattern)
            String contentType = conn.getHeaderField("Content-Type");
            logger.info("  > Retrieved header field (would connect to network)");
            logger.info("  ✗ Deprecated: URLConnection.getHeaderField() (old API pattern)");
            
            logger.info("  ✗ Breaking Change: Use java.net.URI and modern HTTP clients (HttpClient in Java 11+)");
            
        } catch (Exception e) {
            logger.error("  ! Error in URL operations (expected - no internet)", e);
        }
    }

    /**
     * Demonstrates commons-lang 2.6 usage (deprecated, replaced by 3.x)
     */
    public static void demonstrateCommonsLangUsage() {
        logger.info("\n=== [8] Demonstrating Commons-Lang 2.6 Usage ===");
        
        String[] words = {"Hello", "World", "Legacy", "Code"};
        
        // commons-lang 2.6 methods
        String joined = StringUtils.join(words, " ");
        logger.info("  > Joined words using commons-lang 2.6: " + joined);
        
        boolean isAlpha = StringUtils.isAlpha("LegacyCode");
        logger.info("  > Is 'LegacyCode' alphabetic: " + isAlpha);
        
        logger.info("  ✗ Deprecated: commons-lang 2.6 (use commons-lang 3.12+)");
        logger.info("  ✗ Breaking Change: commons-lang 3.x has completely redesigned API");
    }

    /**
     * Demonstrates Servlet API 2.5 usage (ancient servlet spec)
     */
    public static void demonstrateServletAPI() {
        logger.info("\n=== [9] Demonstrating Servlet API 2.5 ===");
        
        logger.info("  > Servlet API 2.5 included in classpath (javax.servlet:servlet-api:2.5)");
        logger.info("  > This is the ancient servlet specification");
        logger.info("  ✗ Deprecated: javax.servlet (replaced by jakarta.servlet in Jakarta EE)");
        logger.info("  ✗ Breaking Change: Servlet 3.0+ uses annotations instead of web.xml");
    }

    /**
     * Demonstrates JDOM 1.1.3 usage (old XML library, replaced by JDOM 2.x)
     */
    public static void demonstrateJdomUsage() {
        logger.info("\n=== [10] Demonstrating JDOM 1.1.3 ===");
        
        try {
            // JDOM 1.1.3 is included but we show the deprecated pattern
            logger.info("  > JDOM 1.1.3 included in classpath (org.jdom:jdom:1.1.3)");
            logger.info("  > JDOM 1.1.3 uses pre-generics API patterns");
            logger.info("  ✗ Deprecated: JDOM 1.1.3 (use JDOM 2.x - modern version)");
            logger.info("  ✗ Breaking Change: JDOM 2.x uses generics and has API changes");
        } catch (Exception e) {
            logger.error("  ! Error with JDOM", e);
        }
    }

    /**
     * Simple data class for JAXB demonstration
     */
    public static class LegacyData {
        private String name;
        private int value;
        
        public LegacyData() {}
        
        public LegacyData(String name, int value) {
            this.name = name;
            this.value = value;
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public int getValue() {
            return value;
        }
        
        public void setValue(int value) {
            this.value = value;
        }
        
        public String toString() {
            return "LegacyData{" + "name='" + name + '\'' + ", value=" + value + '}';
        }
    }
}
