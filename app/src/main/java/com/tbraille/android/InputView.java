package com.tbraille.android;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class InputView extends View {
    public InputView(Context context) {
        super(context);
        init();
    }

    private void init() {

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }


}
