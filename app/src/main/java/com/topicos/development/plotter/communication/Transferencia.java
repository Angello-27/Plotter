package com.topicos.development.plotter.communication;

import android.bluetooth.BluetoothSocket;

import com.topicos.development.plotter.model.Figura;

import java.io.IOException;

public class Transferencia {

    public static void enviar(BluetoothSocket socket, Figura figura) throws IOException {
        if (!figura.vacia() && socket != null){
            String message = figura.convertTo();
            socket.getOutputStream().write(message.getBytes());
        }
    }

}
