package ru.alexander.igdrasil.object;

import ru.alexander.igdrasil.object.GameObject;

public abstract class Behaviour {
    protected GameObject gameObject;

    public abstract void onStart();
    public abstract void onUpdate();

    public abstract void onDestroy();

    public GameObject getGameObject() {
        return gameObject;
    }
}
