package ru.alexander.igdrasil.scene;


import ru.alexander.igdrasil.scene.object.Behaviour;
import ru.alexander.igdrasil.scene.object.GameObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Scene {
    private final List<GameObject> objects = new ArrayList<>();

    public Scene(InputStream sceneStream) {

    }

    public List<GameObject> getObjects() {
        return objects;
    }



    public static void updateRecursively(GameObject object) {
        if (!object.active) return;

        for (Behaviour behaviour : object.getBehaviours())
            behaviour.onUpdate();

        for (GameObject child : object.getChilds())
            updateRecursively(child);
    }

    public static void startRecursively(GameObject object) {
        if (!object.active) return;

        for (Behaviour behaviour : object.getBehaviours())
            behaviour.onStart();
        for (GameObject child : object.getChilds())
            startRecursively(child);
    }

    public static void destroyRecursively(GameObject object) {
        if (!object.active) return;

        for (Behaviour behaviour : object.getBehaviours())
            behaviour.onDestroy();

        for (GameObject child : object.getChilds())
            destroyRecursively(child);
    }
}
