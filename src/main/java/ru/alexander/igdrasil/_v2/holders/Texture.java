package ru.alexander.igdrasil._v2.holders;

import org.lwjgl.opengl.GL30;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;

public class Texture {

    public final int target = GL_TEXTURE_2D;
    public final int id;
    public final int width;
    public final int height;

    public static final int LINEAR = GL_LINEAR;
    public static final int NEAREST = GL_NEAREST;

    public static final int CLAMP = GL_CLAMP;
    public static final int CLAMP_TO_EDGE = GL_CLAMP_TO_EDGE;
    public static final int REPEAT = GL_REPEAT;

    public Texture(File textureFile) {
        this(textureFile, GL_NEAREST);
    }
    public Texture(ByteBuffer data) {
        this(data, GL_NEAREST);
    }

    public Texture(File textureFile, int filter) {
        this(textureFile, filter, GL_CLAMP_TO_EDGE);
    }
    public Texture(ByteBuffer data, int filter) {
        this(data, filter, GL_CLAMP_TO_EDGE);
    }

    public Texture(File textureFile, int filter, int wrap) {
        ByteBuffer buffer;
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            String filePath = textureFile.getAbsolutePath();
            buffer = STBImage.stbi_load(filePath, w, h, channels, 4);
            if(buffer == null) {
                throw new Exception("Can't load file " + filePath + " "+ STBImage.stbi_failure_reason());
            }

            //enable textures and generate an ID
            glEnable(target);
            id = glGenTextures();
            width = w.get();
            height = h.get();

            //bind texture
            bind();

            //setup unpack mode
            glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

            //setup parameters
            glTexParameteri(target, GL_TEXTURE_MIN_FILTER, filter);
            glTexParameteri(target, GL_TEXTURE_MAG_FILTER, filter);
            glTexParameteri(target, GL_TEXTURE_WRAP_S, wrap);
            glTexParameteri(target, GL_TEXTURE_WRAP_T, wrap);

            //pass RGBA data to OpenGL
            glTexImage2D(target, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

            GL30.glGenerateMipmap(target);
            STBImage.stbi_image_free(buffer);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Texture(ByteBuffer data, int filter, int wrap) {
        ByteBuffer buffer;
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            buffer = STBImage.stbi_load_from_memory(data, w, h, channels, 4);
            if(buffer == null) {
                throw new Exception("Can't load texture "+ STBImage.stbi_failure_reason());
            }

            //enable textures and generate an ID
            glEnable(target);
            id = glGenTextures();
            width = w.get();
            height = h.get();

            //bind texture
            bind();

            //setup unpack mode
            glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

            //setup parameters
            glTexParameteri(target, GL_TEXTURE_MIN_FILTER, filter);
            glTexParameteri(target, GL_TEXTURE_MAG_FILTER, filter);
            glTexParameteri(target, GL_TEXTURE_WRAP_S, wrap);
            glTexParameteri(target, GL_TEXTURE_WRAP_T, wrap);

            //pass RGBA data to OpenGL
            glTexImage2D(target, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

            GL30.glGenerateMipmap(target);
            STBImage.stbi_image_free(buffer);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void bind() {
        glBindTexture(target, id);
    }
}

