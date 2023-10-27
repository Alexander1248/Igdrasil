package ru.alexander.igdrasil.vectors;

public class Vector3I {
    public int x;
    public int y;
    public int z;

    public Vector3I(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Vector3I(Vector3 vec) {
        x = (int) vec.x;
        y = (int) vec.y;
        z = (int) vec.z;
    }

    public Vector3I clone() {
        return new Vector3I(x, y, z);
    }

    public static final Vector3I zero = new Vector3I(0,0,0);
    public static final Vector3I one = new Vector3I(1,1,1);

    //==========================
    //          Math
    //==========================
    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }
    public void normalise() {
        double l = length();
        x /= l;
        y /= l;
        z /= l;
    }

    public static Vector3I add(Vector3I a, Vector3I b) {
        return new Vector3I(a.x + b.x, a.y + b.y, a.z + b.z);
    }
    public static Vector3I sub(Vector3I a, Vector3I b) {
        return new Vector3I(a.x - b.x, a.y - b.y, a.z - b.z);
    }
    public static Vector3I mul(Vector3I vec, int val) {
        return new Vector3I(vec.x * val, vec.y * val, vec.z * val);
    }
    public static Vector3 mul(Vector3I vec, double val) {
        return new Vector3(vec.x * val, vec.y * val, vec.z * val);
    }

    public static double distance(Vector3I a, Vector3I b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2) + Math.pow(a.z - b.z, 2));
    }

    public static double dot(Vector3I a, Vector3I b) {
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }
    public static Vector3I cross(Vector3I a, Vector3I b) {
        return new Vector3I(
                a.y * b.z - a.z * b.y,
                a.z * b.x - a.x * b.z,
                a.x * b.y - a.y * b.x);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector3I vector3I = (Vector3I) o;
        return x == vector3I.x && y == vector3I.y && z == vector3I.z;
    }
}
