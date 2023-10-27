package ru.alexander.igdrasil.utils;

import java.io.InputStream;

public class Resources {
    public static InputStream load(String name) {
        return Resources.class.getClassLoader().getResourceAsStream(name);
    }
}
