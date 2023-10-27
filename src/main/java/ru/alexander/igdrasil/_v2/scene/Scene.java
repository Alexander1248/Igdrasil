package ru.alexander.igdrasil._v2.scene;


import ru.alexander.igdrasil._v2.scene.object.GameObject;
import ru.alexander.igdrasil._v2.utils.Resources;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Scene {
    private final List<GameObject> objects = new ArrayList<>();

    public Scene(InputStream sceneStream) {

    }

    public List<GameObject> getObjects() {
        return objects;
    }
}
