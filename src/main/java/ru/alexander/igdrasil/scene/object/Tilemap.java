package ru.alexander.igdrasil.scene.object;

import ru.alexander.igdrasil.scene.object.behaviours.renderers.TilemapRenderer;
import ru.alexander.igdrasil.vectors.Vector2;

public class Tilemap extends GameObject {

    public Tilemap(GameObject parent, Vector2 position, Vector2 size, double rotation) {
        super(parent, position, size, rotation);
        addBehaviour(new TilemapRenderer());
    }

    public Tilemap(GameObject parent, Vector2 position, Vector2 size, double rotation, int level) {
        super(parent, position, size, rotation);
        addBehaviour(new TilemapRenderer(level));

    }
}
