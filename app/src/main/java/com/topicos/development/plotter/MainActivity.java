package com.topicos.development.plotter;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.thebrownarrow.permissionhelper.ActivityManagePermission;
import com.thebrownarrow.permissionhelper.PermissionResult;
import com.topicos.development.plotter.communication.Bluetooth;
import com.topicos.development.plotter.constanst.Permission;
import com.topicos.development.plotter.control.Almacenar;
import com.topicos.development.plotter.control.Diseñar;
import com.topicos.development.plotter.control.interfaces.IconListener;
import com.topicos.development.plotter.utils.Lienzo;

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
        Lienzo lienzo = findViewById(R.id.surface_view);
        this.diseño = new Diseñar(lienzo);
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
                this.diseño.onCreate();
                break;
            case R.id.image_new:
                this.diseño.onAttach();
                break;
            case R.id.button_abierto:
                this.diseño.onFinish(false);
                break;
            case R.id.button_cerrado:
                this.diseño.onFinish(true);
                break;
            case R.id.image_load:
                checkPermission(false);
                break;
            case R.id.image_save:
                checkPermission(true);
                break;
            case R.id.image_print:
                this.diseño.onPrint(this);
                break;
        }
    }

    @Override
    public void onMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onShowButton(boolean mostrar) {
        if (mostrar)
            findViewById(R.id.linear_layout).setVisibility(View.VISIBLE);
        else
            findViewById(R.id.linear_layout).setVisibility(View.GONE);
    }

    public void checkPermission(boolean option) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            askCompactPermissions(Permission.PERMISSION_STORE, new PermissionHelper(option));
        else {
            if (option)
                this.diseño.onSave();
            else
                this.diseño.onLoad();
        }

    }

    private class PermissionHelper implements PermissionResult {

        private boolean option;

        PermissionHelper(boolean option) {
            this.option = option;
        }

        @Override
        public void permissionGranted() {
            if (this.option)
                diseño.onSave();
            else
                diseño.onLoad();
        }

        @Override
        public void permissionDenied() {
            onMessage("Los permisos para la lectura y escritura del disco fueron rechazados");
        }

        @Override
        public void permissionForeverDenied() {
            openSettingsApp(MainActivity.this);
        }
    }
}
