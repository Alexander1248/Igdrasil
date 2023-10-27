package ru.alexander.igdrasil.vectors;

public class Vector4 {
    public double x;
    public double y;
    public double z;
    public double w;

    public Vector4(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4 clone() {
        return new Vector4(x, y, z, w);
    }

    public static final Vector4 zero = new Vector4(0, 0, 0, 0);
    public static final Vector4 one = new Vector4(1, 1, 1, 1);

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

    public static Vector4 add(Vector4 a, Vector4 b) {
        return new Vector4(a.x + b.x, a.y + b.y, a.z + b.z, a.w + b.w);
    }
    public static Vector4 sub(Vector4 a, Vector4 b) {
        return new Vector4(a.x - b.x, a.y - b.y, a.z - b.z, a.w - b.w);
    }
    public static Vector4 mul(Vector4 vec, double val) {
        return new Vector4(vec.x * val, vec.y * val, vec.z * val, vec.w * val);
    }

    public static double distance(Vector4 a, Vector4 b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2) + Math.pow(a.z - b.z, 2) + Math.pow(a.w - b.w, 2));
    }

    public static double dot(Vector4 a, Vector4 b) {
        return a.x * b.x + a.y * b.y + a.z * b.z + a.w * b.w;
    }
}
