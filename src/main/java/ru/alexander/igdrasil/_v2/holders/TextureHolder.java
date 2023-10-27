package ru.alexander.igdrasil._v2.holders;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class TextureHolder {
    private final Map<String, Texture> textures = new HashMap<>();

    public void addTexture(String name, File textureFile) {
        textures.put(name, new Texture(textureFile));
    }
    public void addTexture(String name, File textureFile, int filter) {
        textures.put(name, new Texture(textureFile, filter));
    }

    public void addTexture(String name, File textureFile, int filter, int wrap) {
        textures.put(name, new Texture(textureFile, filter, wrap));
    }

    public Texture getTexture(String name) {
        return textures.get(name);
    }

    public void bindTexture(String name) {
        textures.get(name).bind();
    }


    public boolean hasTexture(String texture) {
        return textures.containsKey(texture);
    }
}
