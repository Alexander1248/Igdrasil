package ru.alexander.igdrasil.scene;

import ru.alexander.igdrasil.scene.object.GameObject;
import ru.alexander.igdrasil.utils.Resources;
import java.util.HashMap;

public class SceneManager {
    private SceneManager() {}

    private static Scene currentScene = null;
    private static final HashMap<String, Scene> scenes = new HashMap<>();


    public static void loadScene(String sceneName, String scenePath) {
        Scene scene = new Scene(Resources.load(scenePath));
        for (GameObject object : scene.getObjects())
            Scene.startRecursively(object);
        scenes.put(sceneName, scene);
    }
    public static void unloadScene(String sceneName) {
        scenes.remove(sceneName);
    }

    public static void changeScene(String sceneName) {
         currentScene = scenes.get(sceneName);
    }
    public static Scene getCurrentScene() {
        return currentScene;
    }
}
