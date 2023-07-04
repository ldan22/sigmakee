package com.articulate.sigma.serializer;

import java.io.IOException;

public interface SerializerService {
    void serializeObject(Object obj, String filename) throws IOException;

    Object deserializeObject(String filename) throws IOException, ClassNotFoundException;
}
