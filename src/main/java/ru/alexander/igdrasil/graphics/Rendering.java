package ru.alexander.igdrasil.graphics;

import ru.alexander.igdrasil.Game;
import ru.alexander.igdrasil.vectors.Vector2;

import static org.lwjgl.opengl.GL11.*;

public class Rendering {

//    public static void renderTexture(Vector2 position, Vector2 size,
//                                     Vector2 uvPos, Vector2 uvSize,
//                                     double rotation, String texture) {
//        double us = uvPos.x + uvSize.x;
//        double vs = uvPos.y + uvSize.y;
//
//        Vector2 rs1 = Vector2.rotate(new Vector2(
//                size.x,
//                size.y
//        ), rotation).mul(0.5);
//
//        Vector2 rs2 = Vector2.rotate(new Vector2(
//                -size.x,
//                size.y
//        ), rotation).mul(0.5);
//
//        if (Game.getTextureHolder().hasTexture(texture))
//            Game.getTextureHolder().bindTexture(texture);
//
//
//        glBegin(GL_QUADS);
//
//        Vector2 pos = Camera.worldToCamera(new Vector2(
//                position.x - rs1.x,
//                position.y - rs1.y
//        ));
//        glVertex2d(pos.x, pos.y);
//        glTexCoord2d(us, vs);
//
//        pos = Camera.worldToCamera(new Vector2(
//                position.x - rs2.x,
//                position.y - rs2.y
//        ));
//        glVertex2d(pos.x, pos.y);
//        glTexCoord2d(us, uvPos.y);
//
//
//        pos = Camera.worldToCamera(new Vector2(
//                position.x + rs1.x,
//                position.y + rs1.y
//        ));
//        glVertex2d(pos.x, pos.y);
//        glTexCoord2d(uvPos.x, uvPos.y);
//
//        pos = Camera.worldToCamera(new Vector2(
//                position.x + rs2.x,
//                position.y + rs2.y
//        ));
//        glVertex2d(pos.x, pos.y);
//        glTexCoord2d(uvPos.x, vs);
//
//        glEnd();
//    }
}
