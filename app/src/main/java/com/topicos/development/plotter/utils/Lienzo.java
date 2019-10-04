package com.topicos.development.plotter.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.topicos.development.plotter.model.Punto;

public class Lienzo extends SurfaceView implements SurfaceHolder.Callback {

    private Lapiz lapiz;
    private Punto punto;
    private SurfaceHolder holder;

    public Lienzo(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.lapiz = new Lapiz();
        this.getHolder().addCallback(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.holder = holder;
        this.punto = new Punto(getWidth() / 100, getHeight() / 100);
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
        punto.setX(event.getX());
        punto.setY(event.getY());
        return super.onTouchEvent(event);
    }

}
