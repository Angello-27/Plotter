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

    private Poligono ultimo() {
        int index = this.poligonos.size() - 1;
        return this.poligonos.get(index);
    }

    public boolean vacia() {
        return this.poligonos.isEmpty();
    }

    public void rellenarPoligono(Punto punto) {
        Poligono poligono = ultimo();
        poligono.addPunto(punto);
    }

    public void formaAbierta() {
        Poligono poligono = ultimo();
        poligono.abierto();
    }

    public Punto formaCerrada() {
        Poligono poligono = ultimo();
        poligono.cerrado();
        return poligono.primero();
    }
}
