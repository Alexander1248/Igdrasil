package ru.alexander.igdrasil.scene;



import ru.alexander.igdrasil.scene.object.GameObject;

import java.util.ArrayList;
import java.util.Collection;

public class ChildList extends ArrayList<GameObject> {
    private final GameObject parent;

    public ChildList(GameObject parent) {
        super();
        this.parent = parent;
    }

    public ChildList(Collection<? extends GameObject> c, GameObject parent) {
        super(c);
        this.parent = parent;
    }

    public ChildList(int initialCapacity, GameObject parent) {
        super(initialCapacity);
        this.parent = parent;
    }

    @Override
    public boolean add(GameObject element) {
        boolean add = super.add(element);
        if (add) element.parent = parent;
        return add;
    }

    @Override
    public void add(int index, GameObject element) {
        super.add(index, element);
        element.parent = parent;
    }

    @Override
    public boolean addAll(Collection<? extends GameObject> c) {
        boolean add = super.addAll(c);
        if (add) c.forEach(gameObject -> gameObject.parent = parent);
        return add;
    }

    @Override
    public boolean addAll(int index, Collection<? extends GameObject> c) {
        boolean add = super.addAll(index, c);
        if (add) c.forEach(gameObject -> gameObject.parent = parent);
        return add;
    }

    @Override
    public GameObject remove(int index) {
        GameObject object = super.remove(index);
        object.parent = null;
        return object;
    }

    @Override
    public boolean remove(Object o) {
        if (super.remove(o)) {
            ((GameObject) o).parent = null;
            return true;
        }
        return false;
    }
}
