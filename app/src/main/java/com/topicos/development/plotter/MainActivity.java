package com.topicos.development.plotter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.os.Build;

import com.thebrownarrow.permissionhelper.ActivityManagePermission;
import com.thebrownarrow.permissionhelper.PermissionResult;
import com.topicos.development.plotter.control.Diseñar;
import com.topicos.development.plotter.control.interfaces.IconListener;
import com.topicos.development.plotter.constanst.Permission;
import com.topicos.development.plotter.utils.Lienzo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends ActivityManagePermission implements
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
                this.diseño.attach();
                break;
            case R.id.image_load:
                break;
            case R.id.image_print:
                break;
            case R.id.image_save:
                checkPermission();
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


    public void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PermissionHelper helper = new PermissionHelper();
            askCompactPermissions(Permission.PERMISSION_STORE, helper);
        }
    }

    private class PermissionHelper implements PermissionResult {

        @Override
        public void permissionGranted() {
        }

        @Override
        public void permissionDenied() {

        }

        @Override
        public void permissionForeverDenied() {
            openSettingsApp(MainActivity.this);
        }
    }
}
