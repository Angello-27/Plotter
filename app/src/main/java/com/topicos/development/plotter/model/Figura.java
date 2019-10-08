package com.topicos.development.plotter.model;

import java.util.ArrayList;

public class Figura {

    private ArrayList<Poligono> poligonos;

    public Figura() {
        this.poligonos = new ArrayList<>();
    }

    public void addPoligono(Poligono poligono) {
        this.poligonos.add(poligono);
    }

    public ArrayList<Poligono> getPoligonos() {
        return this.poligonos;
    }
}
