package com.tbraille.android;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.speech.tts.TextToSpeech;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class InputView extends View {

    private static final String TAG = "count";
    int count;
    float width;
    private TextToSpeech textToSpeech;
    TTSManager ttsManager = null;
    long currentMS;
    int mClickCount;

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

        Log.d(TAG,"mClickCount: "+mClickCount);
        if(mClickCount==2)
        {
            hide();
        }

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

    public void newSession() {
        initSplitter();
        initPath();
        initMapping();
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
        width = x;
        canvas.drawLine(x, 0, x, canvas.getHeight(), splitterPaint);
    }

    private Path path;
    private Paint pathPaint;
    private int[] colors;
    private int colorIndxe;

    private void initPath() {
        path = new Path();
        colors = new int[]{Color.RED, Color.GREEN, Color.BLUE};
        colorIndxe = 0;
        pathPaint = new Paint();
        pathPaint.setColor(colors[colorIndxe]);
        pathPaint.setAntiAlias(true);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStrokeWidth(Setting.getPathWidth());
    }

    private void nextColor() {
        colorIndxe = (colorIndxe + 1) % colors.length;

    }

    private void recordPath(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.reset();
                path.moveTo(event.getX(), event.getY());
                pathPaint.setColor(colors[colorIndxe]);
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
        reset();
    }

    private void reset() {
        halfFlag = false;
        firstHalf = 0;
    }

    private void recognize(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                count = 0;
                record = new double[10000][2];
                currentMS = System.currentTimeMillis();
                break;
            case MotionEvent.ACTION_UP:
                long moveTime = System.currentTimeMillis() - currentMS;
                if(moveTime<200)  {
                    mClickCount++;
                }
                int half = Gesture.getGesture(record, width);
                if (half == 9) {
                    Log.d("gesture", "click");
                } else if (half == 8) {
                    nextColor();
                    if (!halfFlag) {
                        backspace();
                        tts("Backspace");
                    } else {
                        reset();
                        tts("Reset");
                    }
                } else {
                    if (!Setting.getIsLeftAssignTrue()) {
                        half = 7 - half;
                    }
                    if (!halfFlag) {
                        halfFlag = true;
                        firstHalf = half;

                    } else {
                        nextColor();
                        halfFlag = false;
                        int braille = half << 3 | firstHalf;
                        Mapping.MapResult result = mapping.getMapping(braille);
                        Log.d("text", "recognized: " + result.text + " voice: " + result.voice);
                        tts(result.voice);
                        commitText(result.text);
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                record[count][0] = event.getX();
                record[count++][1] = event.getY();
                mClickCount=0;
                break;
        }
    }

    protected void tts(String text){
        ttsManager.initQueue(text);
    }

    private OnCommitTextListener onCommitTextListener;

    public void setOnCommitTextListener(OnCommitTextListener listener) {
        onCommitTextListener = listener;
    }

    private void commitText(String text) {
        if (onCommitTextListener != null) {
            onCommitTextListener.onCommitText(text);
        }
    }

    private OnBackspaceListener onBackspaceListener;

    public void setOnBackspaceListener(OnBackspaceListener listener) {
        onBackspaceListener = listener;
    }

    private void backspace() {
        if (onBackspaceListener != null) {
            onBackspaceListener.onBackspace();
        }
    }

    private OnHideKeyboardListener onHideKeyboardListener;

    public void setOnHideKeyboardListener(OnHideKeyboardListener listener) {
        onHideKeyboardListener = listener;
    }

    private void hide() {
        if (onHideKeyboardListener != null) {
            onHideKeyboardListener.onHideKeyboard();
        }
    }

}
