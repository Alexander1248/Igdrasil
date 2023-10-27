package ru.alexander.igdrasil._v2.scene.object;

import ru.alexander.igdrasil.Game;
import ru.alexander.igdrasil._v2.scene.object.behaviours.CameraBehaviour;
import ru.alexander.igdrasil.vectors.Vector2;
import ru.alexander.igdrasil.vectors.Vector2I;

import java.util.ArrayList;
import java.util.List;

public class Camera extends GameObject {
    public static List<CameraBehaviour> cameras = new ArrayList<>();
    public static CameraBehaviour main = null;


    public static Vector2 worldToCamera(Vector2 worldPos) {
        if (main == null) return null;

        return Vector2.sub(worldPos, main.gameObject.position).div(main.size);
    }
    public static Vector2 cameraToWorld(Vector2 cameraPos) {
        if (main == null) return null;

        return Vector2.mul(cameraPos, main.size).add(main.gameObject.position);
    }


    public static Vector2I worldToScreen(Vector2 worldPos) {
        if (main == null) return null;

        Vector2 cameraPos = worldToCamera(worldPos);
        return new Vector2I(
                (int) (cameraPos.x * Game.getWindow().getWidth()),
                (int) (cameraPos.y * Game.getWindow().getHeight())

        );
    }
    public static Vector2 screenToWorld(Vector2I screenPos) {
        if (main == null) return null;

        Vector2 cameraPos = new Vector2(
                (double) screenPos.x / Game.getWindow().getWidth(),
                (double) screenPos.y / Game.getWindow().getHeight()
        );
        return cameraToWorld(cameraPos);
    }

    public Camera(GameObject parent, Vector2 position, double rotation) {
        super(parent, position, new Vector2(0, 0), rotation);
        addBehaviour(new CameraBehaviour());
    }
    public Camera(GameObject parent, Vector2 position, double rotation, double size) {
        super(parent, position, new Vector2(0, 0), rotation);
        addBehaviour(new CameraBehaviour(size));
    }
}
