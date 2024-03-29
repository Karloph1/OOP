package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * main class.
 */
public class StringFinder {

    /**
     * find.
     */
    public static ArrayList<Integer> find(String fileName, String subString) throws Exception {
        ArrayList<Integer> indices = new ArrayList<>();
        int Capacity = Math.max(2, subString.length() * 2);
        char[] Buffer = new char[Capacity];
        char[] CopyBuffer = new char[Capacity - subString.length() + 1];
        try (FileReader reader = new FileReader(fileName)) {

            int nBuffer = reader.read(Buffer);
            if (nBuffer == -1) {
                return indices;
            }
            int curIndex = 0;

            boolean subFind;
            while (true) {
                subFind = false;
                for (int i = 0; i < nBuffer - subString.length() + 1; i++) {
                    if (Buffer[i] == subString.charAt(0)) {
                        subFind = true;
                        for (int j = i; j < i + subString.length(); j++) {
                            if (Buffer[j] != subString.charAt(j - i)) {
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

                System.arraycopy(Buffer, nBuffer - subString.length() + 1, Buffer, 0, subString.length() - 1);
                nBuffer = reader.read(CopyBuffer);
                if (nBuffer == -1) {
                    break;
                }
                System.arraycopy(CopyBuffer, 0, Buffer, subString.length() - 1, nBuffer);
                nBuffer += subString.length() - 1;
            }
        } catch (IOException e) {
            throw new Exception("no file to read");
        }
        return indices;
    }
}