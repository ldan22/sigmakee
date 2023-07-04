package com.articulate.sigma.serializer;

import java.util.HashMap;
import java.util.Map;

public class SerializerFactory {

    private static final Map<String, SerializerService> serializers = new HashMap<>();

    static {
        serializers.put("fst", new FstSerializer());
        serializers.put("jdk", new JdkSerializer());
    }

    public static SerializerService getSerializer(String implementationName) {
        return serializers.getOrDefault(implementationName, new JdkSerializer());
    }
}
