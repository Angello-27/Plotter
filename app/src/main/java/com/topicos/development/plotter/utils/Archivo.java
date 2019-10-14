package com.topicos.development.plotter.utils;

import android.os.Environment;

import java.io.File;
import java.io.IOException;

public class Archivo {

    public static String folder = "";

    public static String createPath() {
        String absolutePath = Environment.getExternalStorageDirectory().getPath() + folder;
        File file = new File(absolutePath);
        boolean path;
        if ((file.exists()) || file.mkdir()) path = true;
        else path = false;
        return file.getAbsolutePath();
    }

    public static File createFile(String path, String name) throws IOException {
        String absolutePath = path + "/" + name;
        File file = new File(absolutePath);
        boolean created = file.createNewFile();
        if (created)
            return file;
        return null;
    }
}
