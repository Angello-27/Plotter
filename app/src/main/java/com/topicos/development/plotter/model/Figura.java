package com.topicos.development.plotter.model;

import java.util.ArrayList;

public class Figura {

    private ArrayList<Poligono> poligonos;

    public Figura() {
        this.poligonos = new ArrayList<>();
    }

    private Poligono ultimo() {
        int index = this.poligonos.size() - 1;
        return this.poligonos.get(index);
    }

    public boolean vacia() {
        return this.poligonos.isEmpty();
    }

    public boolean incompleto() {
        if (vacia())
            return false;
        Poligono poligono = ultimo();
        return poligono.getForma() != null;
    }

    public void crearPoligono(Punto punto) {
        Poligono poligono = new Poligono();
        poligono.addPunto(punto);
        this.poligonos.add(poligono);
    }

    public void rellenarPoligono(Punto punto) {
        Poligono poligono = ultimo();
        poligono.addPunto(punto);
    }

    public void cerrarPoligono(boolean option) {
        Poligono poligono = ultimo();
        if (option)
            poligono.cerrado();
        else
            poligono.abierto();
    }

    public Punto getPuntoInicial() {
        if (vacia())
            return new Punto(0, 0);
        Poligono poligono = ultimo();
        return poligono.primero();
    }
}
