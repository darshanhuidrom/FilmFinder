package com.kangladevelopers.filmfinder.retrofit.adapter;

import com.kangladevelopers.filmfinder.Utility.Constants;
import com.kangladevelopers.filmfinder.pogo.Actor;
import com.kangladevelopers.filmfinder.pogo.Director;
import com.kangladevelopers.filmfinder.retrofit.interfaces.ActorAPI;
import com.kangladevelopers.filmfinder.retrofit.interfaces.DirectorAPI;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HUIDROM on 6/17/2016.
 */
public class DirectorRestAdapter {
    protected final String TAG = getClass().getSimpleName();
    protected Retrofit retrofit;
    private DirectorAPI directorAPI;

    public DirectorRestAdapter(){
        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        directorAPI = retrofit.create(DirectorAPI.class);
    }
    public Call<Director> getDirectorImageUrl(String director){
        return directorAPI.getDirectorImageUrl(director);
    }
    public Call<Director> getTestDirectorImageUrl(){
        return directorAPI.getTestDirectorImageUrl();
    }
}
