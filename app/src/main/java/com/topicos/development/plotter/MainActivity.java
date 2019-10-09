package com.topicos.development.plotter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.os.Build;
import android.os.Environment;

import androidx.annotation.Nullable;

import com.thebrownarrow.permissionhelper.ActivityManagePermission;
import com.thebrownarrow.permissionhelper.PermissionResult;
import com.topicos.development.plotter.control.Diseñar;
import com.topicos.development.plotter.control.interfaces.IconListener;
import com.topicos.development.plotter.constanst.Permission;
import com.topicos.development.plotter.utils.Lienzo;
import com.topicos.development.plotter.xml.WriteSax;

import java.io.IOException;

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
                String FICHERO = "puntuaciones.xml";
                WriteSax write = new WriteSax(diseño.getFigura());
                try {
                    write.escribir(openFileOutput(FICHERO, Context.MODE_PRIVATE));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e("direccion", getFileStreamPath("new.xml").getAbsolutePath());
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
        //openFolder();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*switch (requestCode) {
            case 0: {
                file = new File(uri.getPath());
            }
        }*/
    }

    public void openFolder() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath());
        intent.setDataAndType(uri, "image/png");
        startActivity(Intent.createChooser(intent, "Open folder"));
    }

    private class PermissionHelper implements PermissionResult {

        @Override
        public void permissionGranted() {
            //openFolder();
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
