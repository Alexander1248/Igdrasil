package ru.alexander.igdrasil;

import ru.alexander.igdrasil.managers.task.TaskManager;
import ru.alexander.igdrasil.managers.tick.TickManager;
import ru.alexander.igdrasil.scene.Scene;
import ru.alexander.igdrasil.graphics.Window;
import ru.alexander.igdrasil.holders.TextureHolder;
import ru.alexander.igdrasil.holders.TranslationHolder;
import ru.alexander.igdrasil.object.Behaviour;
import ru.alexander.igdrasil.object.GameObject;

public class Game {
    private static Window window;

    private static Scene scene;
    private static TickManager tickManager;
    private static TaskManager taskManager;

    private static TextureHolder textureHolder;
    private static TranslationHolder translationHolder;

    private static double deltaTime = 0;
    private static double time = 0;

    public static void init(String windowName, int width, int height, boolean visible,
                            TranslationHolder translationHolder) {
        window = new Window(windowName, width, height, visible);
        scene = new Scene();
        tickManager = new TickManager();
        taskManager = new TaskManager((int) (Runtime.getRuntime().availableProcessors() * 0.5));
        textureHolder = new TextureHolder();
        Game.translationHolder = translationHolder;
    }

    public static void start() {
        window.start();
        taskManager.start();
        for (GameObject object : scene.getObjects()) startRecursively(object);
    }

    public static boolean isAlive() {
        return window.isAlive();
    }

    public static void update() {
        deltaTime = System.nanoTime();
        tickManager.update();
        for (GameObject object : scene.getObjects()) updateRecursively(object);


        time += deltaTime;
        deltaTime = 1e-9 * (System.nanoTime() - deltaTime);
    }

    public static void close() {
        for (GameObject object : scene.getObjects()) destroyRecursively(object);
        taskManager.stop();
        window.close();
    }
    private static void updateRecursively(GameObject object) {
        if (!object.active) return;
        for (Behaviour behaviour : object.getBehaviours()) behaviour.onUpdate();
        for (GameObject child : object.getChilds()) updateRecursively(child);
    }
    private static void startRecursively(GameObject object) {
        for (Behaviour behaviour : object.getBehaviours()) behaviour.onStart();
        for (GameObject child : object.getChilds()) startRecursively(child);
    }
    private static void destroyRecursively(GameObject object) {
        for (Behaviour behaviour : object.getBehaviours()) behaviour.onDestroy();
        for (GameObject child : object.getChilds()) destroyRecursively(child);
    }

    public static Window getWindow() {
        return window;
    }

    public static Scene getScene() {
        return scene;
    }

    public static TickManager getTickManager() {
        return tickManager;
    }

    public static TaskManager getTaskManager() {
        return taskManager;
    }

    public static TextureHolder getTextureHolder() {
        return textureHolder;
    }

    public static TranslationHolder getTranslationHolder() {
        return translationHolder;
    }

    public static double getDeltaTime() {
        return deltaTime;
    }

    public static double getTime() {
        return time;
    }
}
