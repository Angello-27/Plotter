package com.topicos.development.plotter.control;

import com.topicos.development.plotter.communication.Bluetooth;
import com.topicos.development.plotter.communication.Transferencia;
import com.topicos.development.plotter.control.interfaces.ActionListener;
import com.topicos.development.plotter.control.interfaces.PointListener;
import com.topicos.development.plotter.model.Figura;
import com.topicos.development.plotter.model.Punto;
import com.topicos.development.plotter.utils.Lienzo;

import java.io.IOException;

public class Diseñar implements PointListener, ActionListener {

    private Figura figura;
    private Lienzo lienzo;
    private Bluetooth bluetooth;

    public Diseñar(Lienzo lienzo) {
        this.lienzo = lienzo;
        this.figura = new Figura();
        this.bluetooth = new Bluetooth();
        this.lienzo.setListener(this);
    }

    @Override
    public void onCreate() {
        this.figura = new Figura();
        Dibujar.crear(this.lienzo);
    }

    @Override
    public void onAttach() {
        if (this.figura.incompleto())
            Dibujar.reiniciar(this.lienzo);
    }

    @Override
    public void onFinish(boolean option) {
        this.figura.cerrarPoligono(option);
        if (option)
            Dibujar.rellenar(this.lienzo, this.figura.getPuntoInicial());
        Dibujar.terminar(this.lienzo);
    }

    @Override
    public void onSave() {

    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onPrint() {
        if (this.bluetooth.existDevice())
            this.bluetooth.buscarVinculados();
        try {
            this.bluetooth.conectar();
            Transferencia.enviar(this.bluetooth.getSocket(), this.figura);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTouch(float x, float y) {
        Punto punto = new Punto(x, y);
        if (this.figura.vacia() || this.figura.incompleto()) {
            this.figura.crearPoligono(punto);
            Dibujar.agregar(this.lienzo, punto);
        } else {
            figura.rellenarPoligono(punto);
            Dibujar.rellenar(this.lienzo, punto);
        }
    }

}
