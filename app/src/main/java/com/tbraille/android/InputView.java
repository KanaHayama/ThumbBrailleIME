package com.tbraille.android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class InputView extends View {

    private static final String TAG = "count";
    int count;
    int width;

    public double[][] record = new double[10000][2];

    public InputView(Context context) {
        super(context);
        setBackgroundColor(Color.parseColor("#BB000000"));
        initSplitter();
        initPath();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        recordPath(event);
        // judge input

        // refresh
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawSplitter(canvas);
        drawPath(canvas);
    }

    private Paint splitterPaint;

    private void initSplitter() {
        splitterPaint = new Paint();
        splitterPaint.setColor(Color.WHITE);
        splitterPaint.setAntiAlias(true);
        splitterPaint.setStyle(Paint.Style.STROKE);
        splitterPaint.setStrokeWidth(Setting.getSplitterWidth());

    }

    private void drawSplitter(Canvas canvas) {
        float x = canvas.getWidth() * Setting.getSplitterPosition();
        canvas.drawLine(x, 0, x, canvas.getHeight(), splitterPaint);
    }

    private Path path;
    private Paint pathPaint;

    private void initPath() {
        path = new Path();
        pathPaint = new Paint();

        pathPaint.setColor(Color.RED);
        pathPaint.setAntiAlias(true);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStrokeWidth(Setting.getPathWidth());
    }

    private void recordPath(MotionEvent event) {
        Log.d(TAG, "index=" + count++);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.reset();
                path.moveTo(event.getX(), event.getY());
                DisplayMetrics outMetrics = new DisplayMetrics();
                width = outMetrics.widthPixels;
                count = 0;
                record = new double[10000][2];
                break;
            case MotionEvent.ACTION_UP:
                Gesture.getGesture(record,width/2);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX(), event.getY());
                record[count][0] = event.getX();
                record[count++][1] = event.getY();
                break;
        }
    }

    private void drawPath(Canvas canvas) {
        canvas.drawPath(path, pathPaint);
    }

}
