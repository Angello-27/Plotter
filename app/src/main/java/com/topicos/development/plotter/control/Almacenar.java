package com.topicos.development.plotter.control;

import com.topicos.development.plotter.utils.Archivo;

import java.io.File;
import java.io.IOException;

public class Almacenar {

    public static void guardar() {
        Archivo.folder = "/folder";
        String folder = Archivo.createPath();
        try {
            File file = Archivo.createFile(folder, "file.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
