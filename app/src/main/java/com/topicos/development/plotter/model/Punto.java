package com.topicos.development.plotter.model;

public class Punto {

    private int X;
    private int Y;

    public static float width = 1;
    public static float height = 1;

    public Punto(float x, float y) {
        this.X = (int) (x / width);
        this.Y = (int) (y / height);
    }

    public int getX() {
        return this.X;
    }

    public int getY() {
        return this.Y;
    }

    public int getAbsX() {
        return this.X * (int) width;
    }

    public int getAbsY() {
        return this.Y * (int) height;
    }

}
