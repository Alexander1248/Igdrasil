package ru.alexander.igdrasil.graphics;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;
import ru.alexander.igdrasil.object.behaviours.Renderer;
import ru.alexander.igdrasil.vectors.Vector2I;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {
    private final List<Renderer> renderers = new ArrayList<>();
    private final long window;
    private final int width;
    private final int height;
    public boolean visible;

    public Window(String title, int width, int height, boolean visible) {
        this.width = width;
        this.height = height;
        this.visible = visible;
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit())
        {
            System.err.println("Error initializing GLFW");
            System.exit(1);
        }

        glfwWindowHint(GLFW_SAMPLES, 4);

        glfwWindowHint(GLFW_DOUBLEBUFFER, GLFW_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);

        window = glfwCreateWindow(width, height, title, NULL, NULL);



        if (window == NULL)
        {
            System.err.println("Error creating a window");
            System.exit(1);
        }

        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        glfwSetWindowSize(window, width, height);
        glfwSetWindowTitle(window, title);

        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        glfwSwapInterval(1);

        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);


        double prop = (double) height / width;
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(-1, 1, -prop, prop, 1, -1);
        glMatrixMode(GL_MODELVIEW);
    }

    public void start() {
        if (visible) glfwShowWindow(window);
        else glfwHideWindow(window);
    }

    public void update() {
        glfwSwapBuffers(window);
        glfwPollEvents();

        glPushMatrix();
        glLoadIdentity();
        glClear(GL_COLOR_BUFFER_BIT);


        renderers.sort(Renderer::compareTo);
        for (Renderer renderer : renderers)
            if (renderer.getGameObject().getDeepActive())
                renderer.render();

        glFlush();

        glPopMatrix();
    }

    public List<Renderer> getDrawables() {
        return renderers;
    }

    public boolean isAlive() {
        return !glfwWindowShouldClose(window);
    }

    public void close() {
        glDisable(GL_TEXTURE_2D);
        glfwWindowShouldClose(window);
        glfwDestroyWindow(window);
        glfwTerminate();

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
