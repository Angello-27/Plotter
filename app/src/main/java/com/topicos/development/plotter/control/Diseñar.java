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

    public Figura getFigura() {
        return figura;
    }

    public void setLienzo(Lienzo lienzo) {
        this.lienzo = lienzo;
        this.lienzo.setListener(this);
    }

    private boolean incompleto() {
        return this.figura.incompleto();
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
        this.figura.formaAbierta();
        Dibujar.terminar(this.lienzo);
    }

    public void cerrado() {
        Punto punto = this.figura.formaCerrada();
        Dibujar.rellenar(this.lienzo, punto);
        Dibujar.terminar(this.lienzo);
    }

    @Override
    public void onTouch(float x, float y) {
        Punto punto = new Punto(x, y);
        if (this.figura.vacia())
            crearPoligono(punto);
        else if (incompleto())
            rellenar(punto);
        else
            crearPoligono(punto);
    }

    public void attach() {
        if (!incompleto())
            Dibujar.reiniciar(this.lienzo);
    }
}
