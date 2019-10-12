package com.topicos.development.plotter.utils;

import android.os.Environment;

import java.io.File;
import java.io.IOException;

public class Archivo {

    public String createPath() {
        String absolutePath = Environment.getExternalStorageDirectory().getPath() + "/Plotter";
        File file = new File(absolutePath);
        boolean created = file.mkdirs();
        return file.getAbsolutePath();
    }

    public File createFile(String path, String name) throws IOException {
        String absolutePath = path + "/" + name;
        File file = new File(absolutePath);
        boolean created = file.createNewFile();
        if (created)
            return file;
        return null;
    }
}
