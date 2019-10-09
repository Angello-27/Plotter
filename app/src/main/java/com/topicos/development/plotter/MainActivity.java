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

    private Diseñar diseño;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        onBind();
    }

    private void init() {
        this.diseño = new Diseñar();
        Lienzo lienzo = findViewById(R.id.surface_view);
        this.diseño.setLienzo(lienzo);
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
                this.diseño.create();
                break;
            case R.id.image_new:
                break;
            case R.id.image_load:
                break;
            case R.id.image_print:
                break;
            case R.id.image_save:
                break;
            case R.id.button_abierto:
                this.diseño.abierto();
                break;
            case R.id.button_cerrado:
                this.diseño.cerrado();
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
