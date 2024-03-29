package com.topicos.development.plotter.xml;

import android.util.Xml;

import com.topicos.development.plotter.model.Figura;
import com.topicos.development.plotter.model.Poligono;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.OutputStream;

public class WriteSax {

    private Figura figura;
    private static String FICHERO = "puntuaciones.xml";

    public WriteSax(Figura figura) {
        this.figura = figura;
    }

    public void escribir(OutputStream salida) throws IOException {
        XmlSerializer serializador = Xml.newSerializer();
        serializador.setOutput(salida, "UTF-8");
        serializador.startDocument("UTF-8", true);
        serializador.startTag("", figura.getClass().getName());
        serializador.endTag("", figura.getClass().getName());
        serializador.endDocument();
    }
}
