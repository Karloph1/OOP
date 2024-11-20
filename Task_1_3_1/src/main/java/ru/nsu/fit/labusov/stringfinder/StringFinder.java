package ru.nsu.fit.labusov.stringfinder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * StringFinder class.
 */
public class StringFinder {

    /**
     * find string method.
     */
    public static int[] find(String fileName, String subString) throws Exception {
        ArrayList<Integer> indices = new ArrayList<>();
        String utf8SubString = new String(subString.getBytes(), StandardCharsets.UTF_8);

        int capacity = Math.max(2, utf8SubString.length() * 2);
        char[] buffer = new char[capacity];
        char[] copyBuffer = new char[capacity - utf8SubString.length() + 1];

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
        new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            int readBuffer = bufferedReader.read(buffer);

            if (readBuffer == -1) {
                return new int[0];
            }

            int curIndex = 0;

            boolean subFind;
            while (true) {
                for (int i = 0; i < readBuffer - utf8SubString.length() + 1; i++) {
                    if (buffer[i] == utf8SubString.charAt(0)) {
                        subFind = true;
                        for (int j = i; j < i + utf8SubString.length(); j++) {
                            if (buffer[j] != utf8SubString.charAt(j - i)) {
                                subFind = false;
                                break;
                            }
                        }

                        if (subFind) {
                            indices.add(curIndex);
                        }
                    }

                    if (buffer[i] != '\n' && buffer[i] != '\r') {
                        curIndex++;
                    }
                }

                System.arraycopy(buffer, readBuffer - utf8SubString.length() + 1, buffer,
                        0, utf8SubString.length() - 1);
                readBuffer = bufferedReader.read(copyBuffer);

                if (readBuffer == -1) {
                    break;
                }

                System.arraycopy(copyBuffer, 0, buffer, utf8SubString.length() - 1, readBuffer);
                readBuffer += utf8SubString.length() - 1;
            }
        } catch (IOException e) {
            throw new Exception("No file to read");
        }

        int[] ints = new int[indices.size()];

        for (int i = 0; i < indices.size(); i++) {
            ints[i] = indices.get(i);
        }

        return ints;
    }
}
