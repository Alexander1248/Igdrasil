package ru.alexander.igdrasil.scene.object;

public abstract class Behaviour {
    protected GameObject gameObject;

    public abstract void onStart();
    public abstract void onUpdate();

    public abstract void onDestroy();

    public GameObject getGameObject() {
        return gameObject;
    }
}
