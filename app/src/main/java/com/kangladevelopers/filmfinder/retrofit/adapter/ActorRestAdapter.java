package com.kangladevelopers.filmfinder.retrofit.adapter;

import com.kangladevelopers.filmfinder.Utility.Constants;
import com.kangladevelopers.filmfinder.pogo.Actor;
import com.kangladevelopers.filmfinder.retrofit.interfaces.ActorAPI;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ActorRestAdapter {
    protected final String TAG = getClass().getSimpleName();
    protected Retrofit retrofit;
    private ActorAPI actorAPI;

    public ActorRestAdapter(){
        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        actorAPI = retrofit.create(ActorAPI.class);
    }
    public Call<Actor> getActorImageUrl(String actorName){
        return actorAPI.getActorImageUrl(actorName);
    }
    public Call<Actor> getTestActorImageUrl(){
        return actorAPI.getTestActorImageUrl();
    }
}
