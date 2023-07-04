package com.articulate.sigma.serializer;

import org.nustaq.serialization.FSTObjectInput;
import org.nustaq.serialization.FSTObjectOutput;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FstSerializer implements SerializerService {

    @Override
    public void serializeObject(Object obj, String filename) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(filename);
        FSTObjectOutput out = new FSTObjectOutput(fileOut);
        out.writeObject(obj);
        out.close();
        fileOut.close();
    }

    @Override
    public Object deserializeObject(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(filename);
        FSTObjectInput in = new FSTObjectInput(fileIn);
        Object obj = in.readObject();
        in.close();
        fileIn.close();
        return obj;
    }
}
