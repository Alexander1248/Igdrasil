package ru.alexander.igdrasil._v2.vectors;

import java.util.Objects;

public class Vector2I {
    public int x;
    public int y;

    public Vector2I(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Vector2I(Vector2 vec) {
        x = (int) vec.x;
        y = (int) vec.y;
    }

    public Vector2I clone() {
        return new Vector2I(x, y);
    }

    public static final Vector2I zero = new Vector2I(0, 0);
    public static final Vector2I one = new Vector2I(1, 1);

    //==========================
    //          Math
    //==========================
    public double length() {
        return Math.sqrt(x * x + y * y);
    }
    public void normalise() {
        double l = length();
        x /= l;
        y /= l;
    }

    public static Vector2I add(Vector2I a, Vector2I b) {
        return new Vector2I(a.x + b.x, a.y + b.y);
    }
    public static Vector2I sub(Vector2I a, Vector2I b) {
        return new Vector2I(a.x - b.x, a.y - b.y);
    }
    public static Vector2I mul(Vector2I vec, int val) {
        return new Vector2I(vec.x * val, vec.y * val);
    }
    public static Vector2 mul(Vector2I vec, double val) {
        return new Vector2(vec.x * val, vec.y * val);
    }

    public static double distance(Vector2I a, Vector2I b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    public static double dot(Vector2I a, Vector2I b) {
        return a.x * b.x + a.y * b.y;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2I vector2I = (Vector2I) o;
        return x == vector2I.x && y == vector2I.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
