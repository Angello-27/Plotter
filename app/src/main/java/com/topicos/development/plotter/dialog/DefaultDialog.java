package com.topicos.development.plotter.dialog;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.topicos.development.plotter.R;

public class DefaultDialog {

    public static AlertDialog create(Context context, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogStyle);
        builder.setTitle(R.string.app_name);
        builder.setPositiveButton("ok", listener);
        return builder.create();
    }
}
