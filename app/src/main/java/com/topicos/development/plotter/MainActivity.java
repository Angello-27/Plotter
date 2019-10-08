package com.topicos.development.plotter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.topicos.development.plotter.model.Poligono;
import com.topicos.development.plotter.model.Punto;
import com.topicos.development.plotter.utils.Lienzo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Lienzo lienzo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        redimensionar();
        onBind();
    }

    private void redimensionar() {
        this.lienzo = findViewById(R.id.surface_view);
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
            case R.id.image_load:
                break;
            case R.id.image_new:
                break;
            case R.id.image_create:
                lienzo.reset();
                break;
            case R.id.image_print:
                break;
            case R.id.image_save:
                Poligono poligono = lienzo.getPoligono();
                lienzo.reset();
                break;
            case R.id.button_abierto:
                break;
            case R.id.button_cerrado:
                break;
        }
    }
}
