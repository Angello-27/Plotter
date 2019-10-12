package com.topicos.development.plotter.control;

import android.util.Log;

import com.topicos.development.plotter.utils.Archivo;

import java.io.File;
import java.io.IOException;

public class Almacenar {

    public static void guardar() {
        Archivo archivo = new Archivo();
        String folder = archivo.createPath();
        try {
            File file = archivo.createFile(folder, "file.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
