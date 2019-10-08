package com.topicos.development.plotter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.topicos.development.plotter.control.Dibujar;
import com.topicos.development.plotter.control.Diseñar;
import com.topicos.development.plotter.control.interfaces.IconListener;
import com.topicos.development.plotter.utils.Lienzo;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener, IconListener {

    private Lienzo lienzo;
    private Diseñar diseño;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        onBind();
        redimensionar();
    }

    private void init(){
        this.diseño = new Diseñar();
        this.lienzo = findViewById(R.id.surface_view);
        this.lienzo.setListener(this.diseño);
    }

    private void redimensionar() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.widthPixels;
        lienzo.getLayoutParams().height = height;
    }

    private void onBind() {
        findViewById(R.id.image_new).setOnClickListener(this);
        findViewById(R.id.image_load).setOnClickListener(this);
        findViewById(R.id.image_print).setOnClickListener(this);
        findViewById(R.id.image_save).setOnClickListener(this);
        findViewById(R.id.image_create).setOnClickListener(this);
        findViewById(R.id.button_abierto).setOnClickListener(this);
        findViewById(R.id.button_cerrado).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_create:
                Dibujar.crear(lienzo);
                break;
            case R.id.image_load:
                Dibujar.abrir(lienzo);
                break;
            case R.id.image_new:
                Dibujar.agregar(lienzo);
                break;
            case R.id.image_print:
                Dibujar.imprimir();
                break;
            case R.id.image_save:
                break;
            case R.id.button_abierto:
                diseño.abierto();
                break;
            case R.id.button_cerrado:
                diseño.cerrado();
                break;
        }
    }

    @Override
    public void onShowButton(boolean mostrar) {
        if (mostrar)
            findViewById(R.id.linear_layout).setVisibility(View.VISIBLE);
        else
            findViewById(R.id.linear_layout).setVisibility(View.GONE);
    }

}
