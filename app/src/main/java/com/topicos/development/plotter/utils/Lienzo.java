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

import com.topicos.development.plotter.R;
import com.topicos.development.plotter.control.interfaces.IconListener;
import com.topicos.development.plotter.control.interfaces.PointListener;
import com.topicos.development.plotter.model.Punto;

public class Lienzo extends SurfaceView implements SurfaceHolder.Callback {

    private Path path;
    private boolean nuevo;
    private boolean pintar;
    private Lapiz lapiz;
    private SurfaceHolder holder;
    private PointListener listener;
    private IconListener iconListener;

    public Lienzo(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.nuevo = true;
        this.pintar = true;
        this.lapiz = new Lapiz();
        this.lapiz.setColor(Color.RED);
        this.getHolder().addCallback(this);
        this.iconListener = (IconListener) context;
    }

    public void setListener(PointListener listener){
        this.listener = listener;
    }

    public void reset() {
        Canvas canvas = holder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        holder.unlockCanvasAndPost(canvas);
    }

    public void setPintar(boolean pintar) {
        this.pintar = pintar;
    }

    private void dibujar(float X, float Y) {
        if (path.isEmpty())
            this.path.moveTo(X, Y);
        else {
            this.path.lineTo(X, Y);
            this.iconListener.onShowButton(true);
        }
        pintar();
    }

    private void pintar() {
        Canvas canvas = holder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        canvas.drawPath(path, lapiz.getPaint());
        holder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.holder = holder;
        this.path = new Path();
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
        if (pintar && event.getAction() == MotionEvent.ACTION_DOWN) {
            this.listener.onTouch(event.getX(), event.getY(), this.nuevo);
            dibujar(event.getX(), event.getY());
        }
        this.nuevo = false;
        invalidate();
        return true;
    }

}
