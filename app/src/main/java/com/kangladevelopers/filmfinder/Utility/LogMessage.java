package com.kangladevelopers.filmfinder.Utility;

import android.util.Log;
import android.widget.Toast;

import com.kangladevelopers.filmfinder.MyApplication;

/**
 * Created by HUIDROM on 3/12/2016.
 */
public class LogMessage {

    public static void printLog(String tag,String msg){
        Log.d(tag+">>>>>>",msg);
    }
    public static void showToast(String msg){
        Toast.makeText(MyApplication.getAppContext(),msg,Toast.LENGTH_LONG).show();
    }
}
