package com.topicos.development.plotter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.topicos.development.plotter.model.Poligono;
import com.topicos.development.plotter.utils.Lienzo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Lienzo lienzo;
    private Poligono poligono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        onBind();
    }

    private void init() {
        this.lienzo = findViewById(R.id.surface_view);
        this.poligono = new Poligono();
        this.lienzo.setPoligono(poligono);
    }

    private void onBind() {
        findViewById(R.id.image_new).setOnClickListener(this);
        findViewById(R.id.image_load).setOnClickListener(this);
        findViewById(R.id.image_print).setOnClickListener(this);
        findViewById(R.id.image_save).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_load:
                break;
            case R.id.image_new:
                poligono = new Poligono();
                lienzo.setPoligono(poligono);
                lienzo.reset();
                break;
            case R.id.image_print:
                break;
            case R.id.image_save:
                break;
        }
    }
}
