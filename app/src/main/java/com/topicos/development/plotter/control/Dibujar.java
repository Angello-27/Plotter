package com.topicos.development.plotter.control;

import com.topicos.development.plotter.model.Punto;
import com.topicos.development.plotter.utils.Lienzo;

public class Dibujar {

    static void crear(Lienzo lienzo){
        lienzo.reset();
        lienzo.setPintar(true);
    }

    public static void agregar(Lienzo lienzo, Punto punto){
        lienzo.restart();
        lienzo.colocar(punto.getAbsX(), punto.getAbsY());
    }

    public static void abrir(Lienzo lienzo){

    }

    public static void guardar(Lienzo lienzo){

    }

    public static void imprimir(){

    }

}
