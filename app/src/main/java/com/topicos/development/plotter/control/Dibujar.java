package com.topicos.development.plotter.control;

import com.topicos.development.plotter.model.Punto;
import com.topicos.development.plotter.utils.Lienzo;

public class Dibujar {

    static void crear(Lienzo lienzo) {
        lienzo.reset();
    }

    static void agregar(Lienzo lienzo, Punto punto) {
        lienzo.restart();
        lienzo.colocar(punto.getAbsX(), punto.getAbsY());
    }

    static void rellenar(Lienzo lienzo, Punto punto) {
        lienzo.dibujar(punto.getAbsX(), punto.getAbsY());
    }

    static void abrir(Lienzo lienzo) {

    }

    static void guardar(Lienzo lienzo) {

    }

    static void imprimir() {

    }

}
