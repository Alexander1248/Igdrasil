package ru.alexander.igdrasil.scene.object.behaviours;

import ru.alexander.igdrasil.Game;
import ru.alexander.igdrasil.scene.object.Behaviour;

public abstract class Renderer extends Behaviour implements Comparable<Renderer> {

    public int level = 0;

    public Renderer(int level) {
        this.level = level;
    }

    public Renderer() {
    }

    @Override
    public void onStart() {
//        Game.getWindow().getDrawables().add(this);
    }

    public abstract void render();

    @Override
    public void onDestroy() {
//        Game.getWindow().getDrawables().remove(this);

    }

    @Override
    public int compareTo(Renderer o) {
        return level - o.level;
    }
}
