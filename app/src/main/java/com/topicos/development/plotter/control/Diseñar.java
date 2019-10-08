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
    public void onTouch(float x, float y, boolean nuevo) {
        Punto punto = new Punto(x, y);
        if (nuevo)
            crearPoligono(punto);
        else
            rellenar(punto);
    }

    private void crearPoligono(Punto punto) {
        Poligono poligono = new Poligono();
        poligono.addPunto(punto);
        figura.addPoligono(poligono);
    }

    private void rellenar(Punto punto){
        figura.rellenarPoligono(punto);
    }
}
