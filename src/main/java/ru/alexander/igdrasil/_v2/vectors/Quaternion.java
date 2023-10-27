package ru.alexander.igdrasil._v2.vectors;

public class Quaternion {
    public double w;
    public double i;
    public double j;
    public double k;

    public Quaternion(double angle, Vector3 vecDir) {
        this.w = Math.cos(angle / 2);
        i = vecDir.x * Math.sin(angle / 2);
        j = vecDir.y * Math.sin(angle / 2);
        k = vecDir.z * Math.sin(angle / 2);
    }

    public Quaternion(double w, double i, double j, double k) {
        this.w = w;
        this.i = i;
        this.j = j;
        this.k = k;
    }

    public Quaternion multiply(Quaternion q) {
        double nw = w * q.w - i * q.i - j * q.j - k * q.k;
        double ni = w * q.i + i * q.w + j * q.k - k * q.j;
        double nj = w * q.j - i * q.k + j * q.w + k * q.i;
        double nk = w * q.k + i * q.j - j * q.i + k * q.w;
        return new Quaternion(nw, ni, nj, nk);
    }

    public Quaternion multiplyToVector(Vector3 v) {
        Quaternion q = new Quaternion(0, v.x, v.y, v.z);
        return multiply(q);
    }

    public Quaternion normalize() {
        double len = Math.sqrt(w * w + i * i + j * j + k * k);
        return new Quaternion(w / len, i / len, j / len, k / len);
    }

    public Quaternion invert() {
        return new Quaternion(w, -i, -j, -k);
    }

    public Vector3 toVector() {
        return new Vector3(i, j, k);
    }


    public static Vector3 transform(Vector3 v, Quaternion q) {
        Quaternion r = q.multiplyToVector(v);
        r = r.multiply(q.invert().normalize());
        return r.toVector();
    }

}
