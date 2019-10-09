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

import com.topicos.development.plotter.control.interfaces.IconListener;
import com.topicos.development.plotter.control.interfaces.PointListener;
import com.topicos.development.plotter.model.Punto;

import java.util.ArrayList;

public class Lienzo extends SurfaceView implements SurfaceHolder.Callback {

    private Path path;
    private Lapiz lapiz;
    private boolean pintar;
    private ArrayList<Path> list;
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
        this.pintar = true;
        this.lapiz = new Lapiz();
        this.list = new ArrayList<>();
    }

    public void reset() {
        this.list = new ArrayList<>();
        Canvas canvas = this.holder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        this.holder.unlockCanvasAndPost(canvas);
    }

    public void restart() {
        this.pintar = true;
        this.path = new Path();
        this.iconListener.onShowButton(false);
    }

    public void finish() {
        this.pintar = false;
        this.list.add(this.path);
        this.iconListener.onShowButton(false);
    }

    public void colocar(float X, float Y) {
        if (path == null)
            this.path = new Path();
        this.path.moveTo(X, Y);
        punto(X, Y);
        invalidate();
    }

    public void dibujar(float X, float Y) {
        this.path.lineTo(X, Y);
        pintar();
        invalidate();
        this.iconListener.onShowButton(true);
    }

    private void pintar() {
        Canvas canvas = this.holder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        for (Path elem : list)
            canvas.drawPath(elem, this.lapiz.getPaint());
        canvas.drawPath(this.path, this.lapiz.getPaint());
        this.holder.unlockCanvasAndPost(canvas);
    }

    private void punto(float X, float Y) {
        Canvas canvas = this.holder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        for (Path elem : list)
            canvas.drawPath(elem, this.lapiz.getPaint());
        canvas.drawCircle(X, Y, 5, this.lapiz.getPaint());
        this.holder.unlockCanvasAndPost(canvas);
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
        if (this.pintar && event.getAction() == MotionEvent.ACTION_DOWN)
            this.listener.onTouch(event.getX(), event.getY());
        return true;
    }

    public void setListener(PointListener listener) {
        this.listener = listener;
    }
}
