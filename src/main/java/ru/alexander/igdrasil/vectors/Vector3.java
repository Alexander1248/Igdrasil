package ru.alexander.igdrasil.vectors;

import java.awt.*;

public class Vector3 {
    public double x;
    public double y;
    public double z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3 clone() {
        return new Vector3(x, y, z);
    }

    public Color toColor() {
        float cx = (float) Math.max(0, Math.min(1, x));
        float cy = (float) Math.max(0, Math.min(1, y));
        float cz = (float) Math.max(0, Math.min(1, z));
        return new Color(cx, cy, cz);
    }
    public static Vector3 fromColor(Color color) {
        return new Vector3((double) color.getRed() / 255, (double) color.getGreen() / 255,(double)  color.getBlue() / 255);
    }

    public static final Vector3 zero = new Vector3(0,0,0);
    public static final Vector3 one = new Vector3(1,1,1);

    //==========================
    //          Math
    //==========================
    public double sqr() {
        return x * x + y * y + z * z;
    }
    public double length() {
        return Math.sqrt(sqr());
    }
    public Vector3 normalise() {
        double l = length();
        x /= l;
        y /= l;
        z /= l;
        return this;
    }

    public Vector3 add(Vector3 val) {
        x += val.x;
        y += val.y;
        z += val.z;
        return this;
    }
    public Vector3 sub(Vector3 val) {
        x -= val.x;
        y -= val.y;
        z -= val.z;
        return this;
    }
    public Vector3 mul(double val) {
        x *= val;
        y *= val;
        z *= val;
        return this;
    }
    public Vector3 div(double val) {
        x /= val;
        y /= val;
        z /= val;
        return this;
    }

    public static Vector3 add(Vector3 a, Vector3 b) {
        return new Vector3(a.x + b.x, a.y + b.y, a.z + b.z);
    }
    public static Vector3 sub(Vector3 a, Vector3 b) {
        return new Vector3(a.x - b.x, a.y - b.y, a.z - b.z);
    }
    public static Vector3 mul(Vector3 vec, double val) {
        return new Vector3(vec.x * val, vec.y * val, vec.z * val);
    }
    public static Vector3 div(Vector3 vec, double val) {
        return new Vector3(vec.x / val, vec.y / val, vec.z / val);
    }

    public static double distance(Vector3 a, Vector3 b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2) + Math.pow(a.z - b.z, 2));
    }

    public static double dot(Vector3 a, Vector3 b) {
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }
    public static Vector3 cross(Vector3 a, Vector3 b) {
        return new Vector3(
                a.y * b.z - a.z * b.y,
                a.z * b.x - a.x * b.z,
                a.x * b.y - a.y * b.x);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector3 vector3 = (Vector3) o;
        return Vector3.distance(vector3, this) < 1e-5;
    }
}
