package ru.alexander.igdrasil.vectors;

public class Vector4I {
    public int x;
    public int y;
    public int z;
    public int w;

    public Vector4I(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    public Vector4I(Vector4 vec) {
        x = (int) vec.x;
        y = (int) vec.y;
        z = (int) vec.z;
        w = (int) vec.w;
    }

    public Vector4I clone() {
        return new Vector4I(x, y, z, w);
    }

    public static final Vector4I zero = new Vector4I(0, 0, 0, 0);
    public static final Vector4I one = new Vector4I(1, 1, 1, 1);

    //==========================
    //          Math
    //==========================
    public double length() {
        return Math.sqrt(x * x + y * y + z * z + w * w);
    }
    public void normalise() {
        double l = length();
        x /= l;
        y /= l;
        z /= l;
        w /= l;
    }

    public static Vector4I add(Vector4I a, Vector4I b) {
        return new Vector4I(a.x + b.x, a.y + b.y, a.z + b.z, a.w + b.w);
    }
    public static Vector4I sub(Vector4I a, Vector4I b) {
        return new Vector4I(a.x - b.x, a.y - b.y, a.z - b.z, a.w - b.w);
    }
    public static Vector4I mul(Vector4I vec, int val) {
        return new Vector4I(vec.x * val, vec.y * val, vec.z * val, vec.w * val);
    }
    public static Vector4 mul(Vector4I vec, double val) {
        return new Vector4(vec.x * val, vec.y * val, vec.z * val, vec.w * val);
    }

    public static double distance(Vector4I a, Vector4I b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2) + Math.pow(a.z - b.z, 2) + Math.pow(a.w - b.w, 2));
    }

    public static double dot(Vector4I a, Vector4I b) {
        return a.x * b.x + a.y * b.y + a.z * b.z + a.w * b.w;
    }
}
