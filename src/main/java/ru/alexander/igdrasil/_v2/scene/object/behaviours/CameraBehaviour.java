package ru.alexander.igdrasil._v2.scene.object.behaviours;

import ru.alexander.igdrasil.Game;
import ru.alexander.igdrasil._v2.scene.object.Behaviour;
import ru.alexander.igdrasil._v2.scene.object.Camera;

public class CameraBehaviour extends Behaviour {

    public double size = 1;

    public CameraBehaviour() {
    }

    public CameraBehaviour(double size) {
        this.size = size;
    }

    @Override
    public void onStart() {
        Camera.cameras.add(this);
        if (Camera.main == null) Camera.main = this;
    }

    @Override
    public void onUpdate() {
        if (this != Camera.main) return;
        Game.getWindow().update();
    }


    @Override
    public void onDestroy() {
        Camera.cameras.remove(this);
        if (Camera.main == this) Camera.main = null;
    }
}
