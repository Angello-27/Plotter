package com.topicos.development.plotter.model;

import java.util.ArrayList;

public class Poligono {
    
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
}
