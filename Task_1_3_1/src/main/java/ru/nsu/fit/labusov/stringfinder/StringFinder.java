package ru.nsu.fit.labusov.stringfinder;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

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

        int Capacity = Math.max(2, utf8SubString.length() * 2);
        char[] Buffer = new char[Capacity];
        char[] CopyBuffer = new char[Capacity - subString.length() + 1];

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            int nBuffer = bufferedReader.read(Buffer);

            if (nBuffer == -1) {
                return new int[0];
            }

            int curIndex = 0;

            boolean subFind;
            while (true) {
                for (int i = 0; i < nBuffer - utf8SubString.length() + 1; i++) {
                    if (Buffer[i] == utf8SubString.charAt(0)) {
                        subFind = true;
                        for (int j = i; j < i + utf8SubString.length(); j++) {
                            if (Buffer[j] != utf8SubString.charAt(j - i)) {
                                subFind = false;
                                break;
                            }
                        }

                        if (subFind) {
                            indices.add(curIndex);
                        }
                    }
                    curIndex++;
                }

                System.arraycopy(Buffer, nBuffer - utf8SubString.length() + 1, Buffer, 0, utf8SubString.length() - 1);
                nBuffer = bufferedReader.read(CopyBuffer);

                if (nBuffer == -1) {
                    break;
                }

                System.arraycopy(CopyBuffer, 0, Buffer, utf8SubString.length() - 1, nBuffer);
                nBuffer += utf8SubString.length() - 1;
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
