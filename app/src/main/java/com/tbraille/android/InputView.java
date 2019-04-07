package com.tbraille.android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.speech.tts.TextToSpeech;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class InputView extends View {

    private static final String TAG = "count";
    int count;
    int width;
    private TextToSpeech textToSpeech;
    TTSManager ttsManager = null;

    public double[][] record;

    public InputView(Context context) {
        super(context);
        ttsManager = new TTSManager();
        ttsManager.init(context);
        DisplayMetrics outMetrics = new DisplayMetrics();
        width = outMetrics.widthPixels;
        setBackgroundColor(Color.parseColor("#BB000000"));
        initSplitter();
        initPath();
        initMapping();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        recordPath(event);
        // judge input
        recognize(event);
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
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.reset();
                path.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX(), event.getY());
                break;
        }
    }

    private void drawPath(Canvas canvas) {
        canvas.drawPath(path, pathPaint);
    }

    private Mapping mapping;
    private boolean halfFlag;
    private int firstHalf;

    private void initMapping() {
        mapping = new Mapping();
        halfFlag = false;
        firstHalf = 0;
    }

    private void recognize(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                count = 0;
                record = new double[10000][2];
                break;
            case MotionEvent.ACTION_UP:
                for (int i = 0; i<10;i++) {
                    Log.d("record", record[i][0] + "");
                }
                int half = Gesture.getGesture(record, width/2);
                if (halfFlag) {
                    halfFlag = false;
                    int braille = half << 3 | firstHalf;
                    Mapping.MapResult result = mapping.getMapping(braille);
                    Log.d("text", "recognized: " + result.text + " voice: " + result.voice);
                    //TODO: call voice
                    tts(result.voice);

                    //TODO: insert text

                } else {
                    halfFlag = true;
                    firstHalf = half;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                record[count][0] = event.getX();
                record[count++][1] = event.getY();
                break;
        }
    }
    protected void tts(String text){
        ttsManager.initQueue(text);
    }

}
