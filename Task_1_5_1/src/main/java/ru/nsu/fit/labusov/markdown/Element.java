package ru.nsu.fit.labusov.markdown;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Element class.
 */
public abstract class Element {
    public void serialize(String fileName) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(fileName);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(this);

        objectOutputStream.close();
    }
}
