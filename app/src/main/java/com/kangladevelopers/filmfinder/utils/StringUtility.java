package com.kangladevelopers.filmfinder.utils;

import android.content.Context;

import com.kangladevelopers.filmfinder.Activity.BaseActivity;
import com.kangladevelopers.filmfinder.MyApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HUIDROM on 3/8/2016.
 */
public class StringUtility {

    public  static String readAsStringFromAsset(String fileName){
        StringBuilder buf=new StringBuilder();
        InputStream is= null;
        String str = null;
        String data=null;
       try{
           is = MyApplication.getAppContext().getAssets().open(fileName);
           BufferedReader in=null;
           in = new BufferedReader(new InputStreamReader(is, "UTF-8"));

           while ((str=in.readLine()) != null) {
               buf.append(str).append("\n");
           }
           in.close();
           data= buf.toString();
       }
       catch (Exception e){

       }
        return data;
    }

    public static  String[] getActorList(){
        String data = readAsStringFromAsset("actors.txt");
        String[] array =data.split("\n");
        return array;
    }

    public static  String[] getDirectorList(){
        String data = readAsStringFromAsset("directors.txt");
        String[] array =data.split("\n");
        return array;
    }

    public static String getAllValuesAsString(List<String> list){
        String dataInString ="";
        for (int i=0;i<list.size();i++){
            dataInString+=list.get(i);
            if(i<list.size()-1)
                dataInString+=",";
        }
        return dataInString;
    }
}
