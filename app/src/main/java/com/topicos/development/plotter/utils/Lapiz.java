package com.topicos.development.plotter.utils;

import android.graphics.Paint;

public class Lapiz {

    private Paint paint;

    public Lapiz() {
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setStrokeWidth(10);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeJoin(Paint.Join.ROUND);
    }

    public void setColor(int color) {
        this.paint.setColor(color);
    }

    public Paint getPaint() {
        return paint;
    }
}
