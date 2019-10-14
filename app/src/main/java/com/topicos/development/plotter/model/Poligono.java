package com.topicos.development.plotter.model;

import java.util.ArrayList;

public class Poligono {

    enum Geometria {
        ABIERTO,
        CERRADO
    }

    private Geometria forma;
    private ArrayList<Punto> puntos;

    Poligono() {
        this.puntos = new ArrayList<>();
    }

    void addPunto(Punto punto) {
        this.puntos.add(punto);
    }

    Geometria getForma() {
        return forma;
    }

    Punto primero(){
        return this.puntos.get(0);
    }

    void abierto() {
        this.forma = Geometria.ABIERTO;
    }

    void cerrado() {
        this.forma = Geometria.CERRADO;
        this.puntos.add(puntos.get(0));
    }

}
