package ru.nsu.fit.labusov.stringfinder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * StringFinder test.
 */
public class StringFinderTest {

    @Test
    void stringFinderSimpleTextTest() throws Exception {
        URL url = this.getClass().getClassLoader().getResource("ShortTest.txt");
        String urlStr = Objects.requireNonNull(url).getFile();
        String file = URLDecoder.decode(urlStr, StandardCharsets.UTF_8);
        int[] a = StringFinder.find(file, "бра");

        Assertions.assertArrayEquals(new int[]{1, 8}, a);
    }

    @Test
    void stringFinderMediumTextTest() throws Exception {
        URL url = this.getClass().getClassLoader().getResource("MediumTest.txt");
        String urlStr = Objects.requireNonNull(url).getFile();
        String file = URLDecoder.decode(urlStr, StandardCharsets.UTF_8);
        int[] a = StringFinder.find(file, "and");

        Assertions.assertArrayEquals(new int[]{320, 485, 586}, a);
    }

    @Test
    void stringFinderSeparateWordTest() throws Exception {
        URL url = this.getClass().getClassLoader().getResource("SeparateWordTest.txt");
        String urlStr = Objects.requireNonNull(url).getFile();
        String file = URLDecoder.decode(urlStr, StandardCharsets.UTF_8);
        int[] a = StringFinder.find(file, "appe");

        Assertions.assertArrayEquals(new int[]{5, 168, 215}, a);
    }

    @Test
    void stringFinderLongTestTest() throws Exception {
        String filePath = "LongTest.txt";
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(filePath));

            for (int i = 0; i < 10000000; i++) {
                writer.write("abc");
            }

            int[] a = StringFinder.find(filePath, "d");

            Assertions.assertArrayEquals(new int[]{}, a);
        } catch (IOException e) {
            throw new Exception("No text");
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    void stringFinderEmptyTextTest() throws Exception {
        URL url = this.getClass().getClassLoader().getResource("EmptyTest.txt");
        String urlStr = Objects.requireNonNull(url).getFile();
        String file = URLDecoder.decode(urlStr, StandardCharsets.UTF_8);
        int[] a = StringFinder.find(file, "вы");

        Assertions.assertArrayEquals(new int[]{}, a);
    }
}
