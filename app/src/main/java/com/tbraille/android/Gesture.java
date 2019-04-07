package com.tbraille.android;


import android.util.Log;

import java.util.Map;

public class Gesture {

    private static final String TAG = "count";

    public static int getGesture(double[][] path, float mid) {
        /*
         * 9:click
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
        Log.d(TAG,"path=" + path.length);
        for(int i = 0; i<path.length; i++){

            length = i;
            Log.d(TAG, "length=" + length);
            if(path[i][0] == 0){
                break;
            }
        }
        if(length < 2){ //TODO: magic number
            Log.d("Click","Click");
            return 9;
        }
        //clear
        if(length!=0) {
            if (path[length - 1][1] <= path[0][1]) {
                return 8;
            }
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
        Log.d(TAG, "t2=" + t);

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
}

