package com.topicos.development.plotter.control;

import com.topicos.development.plotter.control.interfaces.PointListener;
import com.topicos.development.plotter.model.Figura;
import com.topicos.development.plotter.model.Poligono;
import com.topicos.development.plotter.model.Punto;
import com.topicos.development.plotter.utils.Lienzo;

public class Diseñar implements PointListener {

    private Figura figura;
    private Lienzo lienzo;

    public Diseñar() {
        this.figura = new Figura();
    }

    public void setLienzo(Lienzo lienzo) {
        this.lienzo = lienzo;
        this.lienzo.setListener(this);
    }

    public void create() {
        this.figura = new Figura();
        Dibujar.crear(this.lienzo);
    }

    private void crearPoligono(Punto punto) {
        Poligono poligono = new Poligono();
        poligono.addPunto(punto);
        this.figura.addPoligono(poligono);
        Dibujar.agregar(this.lienzo, punto);
    }

    private void rellenar(Punto punto) {
        figura.rellenarPoligono(punto);
        Dibujar.rellenar(this.lienzo, punto);
    }

    public void abierto() {
        Dibujar.terminar(this.lienzo);
    }

    public void cerrado() {
    }

    @Override
    public void onTouch(float x, float y) {
        Punto punto = new Punto(x, y);
        if (this.figura.vacia())
            crearPoligono(punto);
        else
            rellenar(punto);
    }

}
