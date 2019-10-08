package com.topicos.development.plotter.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.topicos.development.plotter.model.Punto;

public class Lienzo extends SurfaceView implements SurfaceHolder.Callback {

    private Path path;
    private Lapiz lapiz;
    private Punto punto;
    private SurfaceHolder holder;

    public Lienzo(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.lapiz = new Lapiz();
        this.getHolder().addCallback(this);
    }

    private void dibujar() {
        Canvas canvas = holder.lockCanvas();
        canvas.drawPath(path, lapiz.getPaint());
        holder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.holder = holder;
        this.path = new Path();
        this.lapiz.setColor(Color.WHITE);
        this.punto = new Punto(getWidth() / 500, getHeight() / 500);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        this.holder = holder;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.punto.setX(event.getX());
        this.punto.setY(event.getY());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.path.moveTo(punto.getAbsX(), punto.getAbsY());
                break;
            case MotionEvent.ACTION_MOVE:
                this.path.lineTo(punto.getAbsX(), punto.getAbsY());
                break;
            default:
                return false;
        }
        dibujar();
        invalidate();
        return true;
    }

}
