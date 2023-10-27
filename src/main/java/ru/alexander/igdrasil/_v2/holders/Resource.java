package ru.alexander.igdrasil._v2.holders;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class Resource {
    private Resource() {}

    public static InputStream getStream(String resourcePath) {
        return Resource.class.getClassLoader().getResourceAsStream(resourcePath);
    }

    public static byte[] readToArray(String resourcePath) throws IOException {
        return getStream(resourcePath).readAllBytes();
    }
    public static ByteBuffer readToBuffer(String resourcePath) throws IOException {
        return ByteBuffer.wrap(getStream(resourcePath).readAllBytes());
    }
}
