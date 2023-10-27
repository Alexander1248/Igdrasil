package ru.alexander.igdrasil.object.behaviours.renderers;

import ru.alexander.igdrasil.Game;
import ru.alexander.igdrasil.graphics.Rendering;
import ru.alexander.igdrasil.object.Camera;
import ru.alexander.igdrasil.object.behaviours.Renderer;
import ru.alexander.igdrasil.vectors.Vector2;

import static org.lwjgl.opengl.GL11.*;

public class SpriteRenderer extends Renderer {
    public String texture;
    public Vector2 uvPos;
    public Vector2 uvSize;


    public SpriteRenderer() {
        uvPos = Vector2.zero.clone();
        uvSize = Vector2.one.clone();
    }

    public SpriteRenderer(String texture) {
        this.texture = texture;
        uvPos = Vector2.zero.clone();
        uvSize = Vector2.one.clone();
    }
    public SpriteRenderer(String texture, Vector2 uvPos, Vector2 uvSize) {
        this.texture = texture;
        this.uvPos = uvPos;
        this.uvSize = uvSize;
    }

    public SpriteRenderer(int level, String texture) {
        super(level);
        this.texture = texture;
        uvPos = Vector2.zero.clone();
        uvSize = Vector2.one.clone();
    }

    public SpriteRenderer(int level, String texture, Vector2 uvPos, Vector2 uvSize) {
        super(level);
        this.texture = texture;
        this.uvPos = uvPos;
        this.uvSize = uvSize;
    }

    @Override
    public void render() {
        if (Camera.main == null) return;
        Rendering.renderTexture(
                gameObject.position, gameObject.size,
                uvPos, uvSize, gameObject.rotation,
                texture);
    }

    @Override
    public void onUpdate() {

    }
}

