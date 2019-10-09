package com.topicos.development.plotter.model;

import java.util.ArrayList;

public class Poligono {

    enum Geometria {
        ABIERTO,
        CERRADO
    }

    private Geometria forma;
    private ArrayList<Punto> puntos;

    public Poligono() {
        this.puntos = new ArrayList<>();
    }

    public void addPunto(Punto punto) {
        this.puntos.add(punto);
    }

    public ArrayList<Punto> getPuntos() {
        return this.puntos;
    }

    public Geometria getForma() {
        return forma;
    }

    public Punto primero(){
        return this.puntos.get(0);
    }

    public void abierto() {
        this.forma = Geometria.ABIERTO;
    }

    public void cerrado() {
        this.forma = Geometria.CERRADO;
        this.puntos.add(puntos.get(0));
    }

}
