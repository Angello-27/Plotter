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
        init();
        this.getHolder().addCallback(this);
        this.iconListener = (IconListener) context;
    }

    private void init() {
        this.nuevo = true;
        this.pintar = true;
        this.lapiz = new Lapiz();
    }

    public void reset() {
        Canvas canvas = holder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        holder.unlockCanvasAndPost(canvas);
    }

    public void restart() {
        this.nuevo = true;
        this.path = new Path();
        iconListener.onShowButton(false);
    }

    public void setPintar(boolean pintar) {
        this.pintar = pintar;
    }

    public void colocar(float X, float Y) {
        if (path != null) {
            this.path.moveTo(X, Y);
            punto(X, Y);
        }
        invalidate();
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

    private void punto(float X, float Y) {
        Canvas canvas = holder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        canvas.drawCircle(X, Y, 10, lapiz.getPaint());
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
        Punto.width = w / 100;
        Punto.height = h / 100;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (pintar && event.getAction() == MotionEvent.ACTION_DOWN)
            this.listener.onTouch(event.getX(), event.getY());
        return true;
    }

    public void setListener(PointListener listener) {
        this.listener = listener;
    }
}
