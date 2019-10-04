package com.topicos.development.plotter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;

import com.topicos.development.plotter.utils.Lienzo;

public class MainActivity extends AppCompatActivity {

    private Lienzo lienzo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //lienzo = findViewById(R.id.surface_view);
    }
}
