package com.example.gesturereg;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "count";
    public TextView pox,poY,condition,domainText;
    public View line;
    public double[][] path = new double[10000][2];
    int count;
    int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pox = (TextView)findViewById(R.id.text1);
        poY = (TextView)findViewById(R.id.text2);
        condition = (TextView)findViewById(R.id.text3);
        domainText = (TextView) findViewById(R.id.domainText);
        line  = (View) findViewById(R.id.line);
        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;
        Log.d("width",width/2+"");


    }
    protected int lastIndex(double[][] path){
        for (int i = 0;i < path.length; i++){
           if(path[i][0] == 0){
               return i-1;
           }
        }
        return 0;
    }


    protected int pathNum(double[][] path, double mid){
        /*
        * 8:clear
        * 7:down-down in left
        * 0:down-down in right
        * 3:down-right
        * 1:right-down
        * 4:down-left
        * 6:left-down
        * 5:right-left
        * 2:left-right
        */
        //length: path length
        int length = 0;
        for(int i = 0; i<path.length; i++){
            length = i;
            if(path[i][0] == 0){
                break;
            }
        }


        Log.d(TAG, "length=" + length);
        //clear
        if(path[length-1][1]<=path[0][1]){
            return 8;
        }

        //down-down in left
        int t = 0;
        for(int i = 0; i<length; i++){
            if(path[i][0]<=mid){
                t++;
            }
            else{
                break;
            }
        }
        Log.d(TAG, "t1=" + t);

        if(t==length){
            return 7;
        }

        //down-down in right
        t = 0;
        for(int i = 0; i<length; i++){
            if(path[i][0]>=mid){
                t++;
            }
            else{
                break;
            }
        }
        Log.d(TAG, "t2=" + t);

        if(t==length){
            return 0;
        }

        //down-right
        //right-down
        if(path[0][0]<mid && path[length-1][0]>mid){
            int turnIndex = 1;
            double deltaAngle = 0;
            double realDeltaAngle = 0;
            for(int i=1; i<length-1; i++){
                double k1 = 10000000;
                if((path[i][0]-path[0][0])!=0){
                    k1 = (path[i][1]-path[0][1])/(path[i][0]-path[0][0]);
                }
                if(k1<=0){
                    k1 = 10000000;
                }
                double k2 = 10000000;
                if((path[length-1][0]-path[i][0])!=0) {
                    k2 = (path[length - 1][1] - path[i][1]) / (path[length - 1][0] - path[i][0]);
                }
                if(k2<=0){
                    k2 = 10000000;
                }

                double dAngle = Math.abs(Math.atan(k1)-Math.atan(k2));
                if(dAngle>deltaAngle){
                    deltaAngle = dAngle;
                    realDeltaAngle = Math.atan(k1)-Math.atan(k2);
                    turnIndex = i;
                }
            }   
            if(realDeltaAngle>0){
                return 3;
            }
            else{
                return 1;
            }
        }

        //down-left
        //left-down
        if(path[0][0]>mid && path[length-1][0]<mid){
            int turnIndex = 1;
            double deltaAngle = 0;
            double realDeltaAngle = 0;
            for(int i=1; i<length-1; i++){
                double k1 = 10000000;
                if((path[i][0]-path[0][0])!=0){
                    k1 = -(path[i][1]-path[0][1])/(path[i][0]-path[0][0]);
                }
                if(k1<=0){
                    k1 = 10000000;
                }

                double k2 = 10000000;
                if((path[length-1][0]-path[i][0])!=0) {
                    k2 = -(path[length-1][1] - path[i][1])/(path[length-1][0] - path[i][0]);
                }
                if(k2<=0){
                    k2 = 10000000;
                }

                double dAngle = Math.abs(Math.atan(k1)-Math.atan(k2));
                if(dAngle>deltaAngle){
                    deltaAngle = dAngle;
                    realDeltaAngle = Math.atan(k1)-Math.atan(k2);
                    turnIndex = i;
                }
            }   
            if(realDeltaAngle>0){
                return 4;
            }
            else{
                return 6;
            }
        }
        //right-left
        if(path[0][0]<mid && path[length-1][0]<mid){
            for(int i = 1; i<length-1; i++){
                if(path[i][0]>mid){
                    return 5;
                }
            } 
        }
        //left-right
        if(path[0][0]>mid && path[length-1][0]>mid){
            for(int i = 1; i<length-1; i++){
                if(path[i][0]<mid){
                    return 2;
                }
            } 
        }
        //wrong
        return 1;
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        double[] position = new double[2];
        float x = event.getX();
        float y = event.getY();
//        try
//        {
            switch(event.getAction())
            {
                case MotionEvent.ACTION_DOWN: pox.setText(""+x);poY.setText(""+y);condition.setText("down");
                    count = 0;
                    path = null;
                    path = new double[10000][2];
                    break;
                case MotionEvent.ACTION_UP:pox.setText(""+x);poY.setText(""+y);condition.setText("up");


                    int p = pathNum(path, width/2);
                    Log.d(TAG, "p=" + p);
                    switch(p){
                        case 7:
                            domainText.setText("down-down in left");
                            break;
                        case 0:
                            domainText.setText("down-down in right");
                            break;
                        case 3:
                            domainText.setText("down-right");
                            break;
                        case 1:
                            domainText.setText("right-down");
                            break;
                        case 4:
                            domainText.setText("down-left");
                            break;
                        case 6:
                            domainText.setText("left-down");
                            break;
                        case 5:
                            domainText.setText("right-left");
                            break;
                        case 2:
                            domainText.setText("left-right");
                            break;
                        case 8:
                            domainText.setText("clear");
                    }

                case MotionEvent.ACTION_MOVE:
                    Log.d(TAG, "index=" + count);
                    pox.setText(""+x);poY.setText(""+y);
                    condition.setText("move");
                    position[0] = x;
                    position[1] = y;
                    path[count] = position;
                    count += 1;
                    break;
            }
            return true;
        }
//        catch(Exception e)
//        {
//            Log.v("touch", e.toString());
//            return false;
//        }
//    }
}
