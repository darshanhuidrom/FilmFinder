package com.kangladevelopers.filmfinder.retrofit.interfaces;

import com.kangladevelopers.filmfinder.pogo.Actor;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by HUIDROM on 6/17/2016.
 */
public interface  ActorAPI {
    @GET("EJP8Q22E-")
    Call<Actor> getActorImageUrl(String actorName);

    @GET("EJP8Q22E-")
    Call<Actor> getTestActorImageUrl();
}
