package ru.alexander.igdrasil.scene.object;

import ru.alexander.igdrasil.scene.object.behaviours.renderers.SpriteRenderer;
import ru.alexander.igdrasil.vectors.Vector2;

public class Sprite extends GameObject {

    public Sprite(GameObject parent, Vector2 position, Vector2 size, double rotation, String texture, int level) {
        super(parent, position, size, rotation);
        addBehaviour(new SpriteRenderer(level, texture));
    }
    public Sprite(GameObject parent, Vector2 position, Vector2 size, double rotation, String texture) {
        this(parent, position, size, rotation, texture, 0);
    }
}
