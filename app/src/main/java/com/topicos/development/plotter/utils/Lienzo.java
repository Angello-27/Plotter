package com.topicos.development.plotter.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.topicos.development.plotter.control.interfaces.PointListener;
import com.topicos.development.plotter.model.Punto;

public class Lienzo extends SurfaceView implements SurfaceHolder.Callback {

    private Path path;
    private boolean pintar;
    private SurfaceHolder holder;
    private PointListener listener;

    public Lienzo(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.pintar = true;
        this.getHolder().addCallback(this);
    }

    public void reset() {
        Canvas canvas = holder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        holder.unlockCanvasAndPost(canvas);
    }

    public void nuevo() {
        this.path = new Path();
    }

    public void setPintar(boolean pintar) {
        this.pintar = pintar;
    }

    private void dibujar(Punto punto) {
        if (path.isEmpty()) {
            this.path.moveTo(punto.getAbsX(), punto.getAbsY());
        } else {
            this.path.lineTo(punto.getAbsX(), punto.getAbsY());
        }
        pintar();
    }

    private void pintar() {
        Canvas canvas = holder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        //canvas.drawPath(path, lapiz.getPaint());
        holder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.holder = holder;
        reset();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        this.holder = holder;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Punto.width = w / 1000;
        Punto.height = h / 1000;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (pintar && event.getAction() == MotionEvent.ACTION_DOWN)
            listener.onTouch(event.getX(), event.getY());
        invalidate();
        return true;
    }

}
