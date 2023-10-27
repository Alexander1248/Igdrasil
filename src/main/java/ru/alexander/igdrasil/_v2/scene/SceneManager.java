package ru.alexander.igdrasil._v2.scene;

import ru.alexander.igdrasil._v2.Game;
import ru.alexander.igdrasil._v2.utils.Resources;
import ru.alexander.igdrasil.object.Behaviour;
import ru.alexander.igdrasil.object.GameObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static ru.alexander.igdrasil.scene.Scene.startRecursively;

public class SceneManager {
    private SceneManager() {}

    private static Scene currentScene = null;
    private static final HashMap<String, Scene> scenes = new HashMap<>();


    public static void loadScene(String sceneName, String scenePath) {
        Scene scene = new Scene(Resources.load(scenePath));
        for (GameObject object : scene.getObjects())
            startRecursively(object);
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
