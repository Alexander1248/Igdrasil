package ru.alexander.igdrasil.scene;

import ru.alexander.igdrasil.object.Behaviour;
import ru.alexander.igdrasil.object.GameObject;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private final List<GameObject> objects = new ArrayList<>();

    public List<GameObject> getObjects() {
        return objects;
    }

    public static void updateRecursively(GameObject object) {
        if (!object.active) return;
        for (Behaviour behaviour : object.getBehaviours()) behaviour.onUpdate();
        for (GameObject child : object.getChilds()) updateRecursively(child);
    }
    public static void startRecursively(GameObject object) {
        if (!object.active) return;
        for (Behaviour behaviour : object.getBehaviours()) behaviour.onStart();
        for (GameObject child : object.getChilds()) startRecursively(child);
    }
    public static void destroyRecursively(GameObject object) {
        if (!object.active) return;
        for (Behaviour behaviour : object.getBehaviours()) behaviour.onDestroy();
        for (GameObject child : object.getChilds())
            destroyRecursively(child);
    }
}
