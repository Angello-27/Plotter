package com.topicos.development.plotter.model;

public class Punto {

    private int X;
    private int Y;
    private int width;
    private int height;

    public Punto(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return this.X;
    }

    public void setX(float x) {
        this.X = (int) x / this.width;
    }

    public int getY() {
        return this.Y;
    }

    public void setY(float y) {
        this.Y = (int) y / this.height;
    }

    public int getAbsX() {
        return this.X * this.width;
    }

    public int getAbsY() {
        return this.Y * this.height;
    }

}
