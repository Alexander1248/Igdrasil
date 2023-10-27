package ru.alexander.igdrasil._v2.scene.object.behaviours.renderers;

import ru.alexander.igdrasil._v2.scene.object.Camera;
import ru.alexander.igdrasil._v2.scene.object.behaviours.Renderer;
import ru.alexander.igdrasil.graphics.Rendering;
import ru.alexander.igdrasil.vectors.Vector2;
import ru.alexander.igdrasil.vectors.Vector2I;

import java.util.HashMap;
import java.util.Map;


public class TilemapRenderer extends Renderer {
    private final Map<Integer, TextureContainer> tiles = new HashMap<>();
    private final Map<Vector2I, Integer> map = new HashMap<>();

    public double density = 1;

    public TilemapRenderer() {
    }

    public TilemapRenderer(int level) {
        super(level);
    }

    public void addTile(int tileID, TextureContainer texture) {
        tiles.put(tileID, texture);
    }
    public void removeTile(int tileID) {
        tiles.remove(tileID);
    }

    public void setTileToMap(Vector2I position, int tileID) {
        map.put(position, tileID);
    }
    public void removeTileFromMap(Vector2I position) {
        map.remove(position);
    }


    @Override
    public void render() {
        if (Camera.main == null) return;
        Vector2 min = Camera.cameraToWorld(new Vector2(-1, -1));
        Vector2 max = Camera.cameraToWorld(new Vector2(1, 1));


        Vector2 size = Vector2.mul(gameObject.size, density);
        Vector2I tilePos = new Vector2I(0, 0);
        for (int y = (int) (Math.floor(min.y) - 1); y < Math.ceil(max.y) + 1; y++)
            for (int x = (int) (Math.floor(min.x) - 1); x < Math.ceil(max.x) + 1; x++) {
                tilePos.x = x;
                tilePos.y = y;
                TextureContainer tex = tiles.get(map.get(tilePos));
                if (tex != null) {
                    double sx = gameObject.position.x + size.x * x;
                    double sy = gameObject.position.y + size.y * y;

                    Rendering.renderTexture(
                            new Vector2(sx, sy), size,
                            tex.uvPos, tex.uvSize,
                            gameObject.rotation,
                            tex.textureName);
                }
            }

    }

    @Override
    public void onUpdate() {

    }

    public static class TextureContainer {
        private String textureName;
        private final Vector2 uvPos;
        private final Vector2 uvSize;

        public TextureContainer(String textureName, Vector2 uvPos, Vector2 uvSize) {
            this.textureName = textureName;
            this.uvPos = uvPos;
            this.uvSize = uvSize;
        }

        public void setTextureName(String textureName) {
            this.textureName = textureName;
        }

        public String getTextureName() {
            return textureName;
        }

        public Vector2 getUvPos() {
            return uvPos;
        }

        public Vector2 getUvSize() {
            return uvSize;
        }
    }
}

