package ru.nsu.fit.labusov.stringfinder;

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
        int[] a = StringFinder.find(file, "answer");

        Assertions.assertArrayEquals(new int[]{1317}, a);
    }

    @Test
    void stringFinderSeparateWordTest() throws Exception {
        URL url = this.getClass().getClassLoader().getResource("SeparateWordTest.txt");
        String urlStr = Objects.requireNonNull(url).getFile();
        String file = URLDecoder.decode(urlStr, StandardCharsets.UTF_8);
        int[] a = StringFinder.find(file, "appe");

        Assertions.assertArrayEquals(new int[]{5, 170, 217}, a);
    }

    /*@Test
    void stringFinderLongTextTest() throws Exception {
        URL url = this.getClass().getClassLoader().getResource("LongTest.txt");
        String urlStr = Objects.requireNonNull(url).getFile();
        String file = URLDecoder.decode(urlStr, StandardCharsets.UTF_8);
        int[] a = StringFinder.find(file, "вы");

        Assertions.assertArrayEquals(new int[]{290, 338, 420, 668, 1159, 1227, 1485, 1879, 1898,
            2558, 2817, 3053, 3223, 3241, 3297, 3437, 3921, 4229, 4285, 4341, 4545, 6054,
            6911, 9577, 10082, 10109, 10187, 11187, 11247, 11355, 11573, 12410, 12509,
            12755, 13451, 14117, 14701}, a);
    }

     */

    @Test
    void stringFinderEmptyTextTest() throws Exception {
        URL url = this.getClass().getClassLoader().getResource("EmptyTest.txt");
        String urlStr = Objects.requireNonNull(url).getFile();
        String file = URLDecoder.decode(urlStr, StandardCharsets.UTF_8);
        int[] a = StringFinder.find(file, "вы");

        Assertions.assertArrayEquals(new int[]{}, a);
    }
}
