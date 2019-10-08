package com.topicos.development.plotter.control;

import com.topicos.development.plotter.control.interfaces.PointListener;
import com.topicos.development.plotter.model.Figura;
import com.topicos.development.plotter.model.Poligono;
import com.topicos.development.plotter.model.Punto;

public class Diseñar implements PointListener {

    private Figura figura;

    public Diseñar() {
        this.figura = new Figura();
    }

    @Override
    public void onTouch(float x, float y) {
        Punto punto = new Punto(x, y);
        if (figura.vacia())
            crearFigura(punto);
    }
    
    private void crearFigura(Punto punto) {
        Poligono poligono = new Poligono();
        poligono.addPunto(punto);
        figura.addPoligono(poligono);
    }
}
