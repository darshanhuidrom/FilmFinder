/*
package com.kangladevelopers.filmfinder.Network;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kangladevelopers.filmfinder.MyApplication;
import com.kangladevelopers.filmfinder.Utility.Constants;
import com.kangladevelopers.filmfinder.Utility.LogMessage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

*
 * Created by BURNI on 15/12/15.


public class VolleySingelton {
    private static VolleySingelton mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mCtx;


    private String Log_TAG = "VolleySingelton";

    private VolleySingelton() {
        mCtx = MyApplication.getAppContext();
        mRequestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized VolleySingelton getInstance() {
        if (mInstance == null) {
            mInstance = new VolleySingelton();
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        //req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);

    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }


    //------------------- PUBLIC METHOD FOR ACTIVITIES TO USE NETWORK  ----------


    public void requestWithSomeHttpHeaders() {
        //RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://www.somewebsite.com";
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.d("ERROR","error => "+error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("User-Agent", "Nintendo Gameboy");
                params.put("Accept-Language", "fr");

                return params;
            }
        };
        getRequestQueue().add(postRequest);

    }



    public void getActorName(String key,String value){
        if(value.contains(" ")){
            value = value.replaceAll(" ","%20");
        }
        String url = Constants.ACTORS_URL + "?"+key+"="+value;


        LogMessage.printLog(Log_TAG, "Actors url: " + url);

        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        if (mainDataListener != null) {
                            mainDataListener.onResponse(response);
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (mainDataListener != null) {
                            mainDataListener.onErrorResponse(error);
                        }
                    }
                });

        getRequestQueue().add(jsonRequest);
 new JsonObjectRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {

                if (mainDataListener != null) {
                    mainDataListener.onResponse(jsonArray);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (mainDataListener != null) {
                    mainDataListener.onErrorResponse(volleyError);
                }
            }
        });

        getRequestQueue().add(jsonArrayRequest);

    }


    public void sendStatusGetString(final HashMap<String, String> param){
        String url = Constants.PostURL;

        StringRequest strReq = new StringRequest(Request.Method.POST,url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String str) {
                        Log.d(Log_TAG, str);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(Log_TAG, "Error: " + error.getMessage());
                postDataListener.onErrorResponse(error);
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = param;
                Log.i("TAG",String.valueOf(params));
                return params;
            }

        };
        getRequestQueue().add(strReq);
    }


    //-----------------------------------------------------------

  public void getMainDataNew(String barcode){
        String url = Constants.MainURL + "?barcode=" + barcode;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try{
                    String status = String.valueOf(response.get("status"));
                    if(status.equalsIgnoreCase("success")){
                        JSONArray jsonArrayData = (JSONArray) response.get("data");
                        if (mainDataListener != null) {
                            mainDataListener.onResponse(jsonArrayData);
                        }

                    }else if (status.equals("error")){
                        String message = String.valueOf(response.get("message"));
                        Log.e(Log_TAG, "Error in request/no header: "+message);
                    }else{
                        //DO SOMETHING
                    }
                }catch(Exception e){
                    Log.e(Log_TAG, "Error/exception in JSON response: "+ String.valueOf(e));
                }


            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                if (mainDataListener != null) {
                    mainDataListener.onErrorResponse(error);
                }
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = getSecureHeader();
                return headers;
            }
        };
        getRequestQueue().add(jsonObjectRequest);
    }



    public void sendStatus(final HashMap<String, String> param){
        JSONObject obj = new JSONObject(param);
        String url = Constants.PostURL;

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,url, obj,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(Log_TAG, response.toString());
                        postDataListener.onResponse(response);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(Log_TAG, "Error: " + error.getMessage());
                postDataListener.onErrorResponse(error);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = getSecureHeader();
                return headers;
            }
        };
        getRequestQueue().add(jsonObjReq);
    }


    private HashMap getSecureHeader(){

        HashMap<String,String> headers = new HashMap();
        long ts = System.currentTimeMillis()/1000;
        String timeStamp = String.valueOf(ts);
        Log.w(Log_TAG,"X-Time Stamp:"+ timeStamp);
        headers.put("X-Timestamp", timeStamp);
        String signature =sha256(Constants.signature.concat(timeStamp));
        Log.w(Log_TAG, "X-Signature: " + signature);
        headers.put("X-Signature", signature);
        return headers;
    }

    //---------------------------------------------------------------

    public static String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }


    //---------------------------------------------INTERFACE-------------------

    private MainDataListener mainDataListener;
    private PostDataListener postDataListener;

    public interface MainDataListener{
        void onResponse(JSONObject jsonArray);
        void onErrorResponse(VolleyError volleyError);
    }

    public interface PostDataListener{
        void onResponse(JSONObject jsonObject);
        void onErrorResponse(VolleyError volleyError);
    }

    //------------------

    public void setMainDataListener(MainDataListener mainDataListener){
        this.mainDataListener = mainDataListener;
    }

    public void setPostDataListener(PostDataListener postDataListener){
        this.postDataListener = postDataListener;
    }

}
*/
