package com.topicos.development.plotter;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.thebrownarrow.permissionhelper.ActivityManagePermission;
import com.thebrownarrow.permissionhelper.PermissionResult;
import com.topicos.development.plotter.constanst.Permission;
import com.topicos.development.plotter.control.Almacenar;
import com.topicos.development.plotter.control.Diseñar;
import com.topicos.development.plotter.control.interfaces.IconListener;
import com.topicos.development.plotter.dialog.DefaultDialog;
import com.topicos.development.plotter.utils.Lienzo;

public class MainActivity extends ActivityManagePermission implements
        View.OnClickListener, IconListener {

    private Diseñar diseño;
    private AlertDialog dialog;

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
        this.dialog = DefaultDialog.create(this, null);
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
                dialog.setMessage("Mensaje de prueba como se veria el dialogo con los nuevos temas");
                dialog.show();
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
        } else
            Almacenar.guardar();
    }

    private class PermissionHelper implements PermissionResult {

        @Override
        public void permissionGranted() {
            Almacenar.guardar();
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
