package com.topicos.development.plotter.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.topicos.development.plotter.model.Poligono;
import com.topicos.development.plotter.model.Punto;

public class Lienzo extends SurfaceView implements SurfaceHolder.Callback {

    private Path path;
    private Lapiz lapiz;
    private Poligono poligono;
    private SurfaceHolder holder;

    public Lienzo(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.lapiz = new Lapiz();
        this.getHolder().addCallback(this);
    }

    public void reset() {
        this.path = new Path();
        Canvas canvas = holder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        holder.unlockCanvasAndPost(canvas);
    }

    public void setPoligono(Poligono poligono) {
        this.poligono = poligono;
    }

    public Poligono getPoligono(){
        return this.poligono;
    }

    private void dibujar(Punto punto) {
        if (path.isEmpty()) {
            this.path.moveTo(punto.getAbsX(), punto.getAbsY());
        }else
            this.path.lineTo(punto.getAbsX(), punto.getAbsY());
        poligono.addPunto(punto);
        pintar();
    }

    private void pintar() {
        Canvas canvas = holder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        canvas.drawPath(path, lapiz.getPaint());
        holder.unlockCanvasAndPost(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Punto.width = w / 500;
        Punto.height = h / 500;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.holder = holder;
        this.lapiz.setColor(Color.RED);
        reset();
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
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Punto punto = new Punto(event.getX(), event.getY());
            dibujar(punto);
        }
        invalidate();
        return true;
    }

}
