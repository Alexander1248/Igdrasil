package ru.alexander.igdrasil.object;

import ru.alexander.igdrasil.scene.ChildList;
import ru.alexander.igdrasil.vectors.Quaternion;
import ru.alexander.igdrasil.vectors.Vector2;
import ru.alexander.igdrasil.vectors.Vector3;

import java.util.ArrayList;

public class GameObject {
    public GameObject parent;
    private final ChildList childs;

    public Vector2 position;
    public Vector2 size;
    public double rotation;

    private final ArrayList<Behaviour> behaviours;
    public boolean active = true;

    public GameObject(GameObject parent, Vector2 position, Vector2 size, double rotation) {
        childs = new ChildList(this);
        behaviours = new ArrayList<>();

        this.parent = parent;
        this.position = position;
        this.size = size;
        this.rotation = rotation;
    }

    public GameObject getParent() {
        return parent;
    }

    public ChildList getChilds() {
        return childs;
    }

    public void addBehaviour(Behaviour behaviour) {
        behaviour.gameObject = this;
        behaviours.add(behaviour);
    }
    public ArrayList<Behaviour> getBehaviours() {
        return behaviours;
    }

    public boolean getDeepActive() {
        if (!active) return false;
        if (parent == null) return true;
        return parent.getDeepActive();
    }
}
