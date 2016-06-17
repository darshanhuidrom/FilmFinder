package com.kangladevelopers.filmfinder.retrofit.interfaces;

import com.kangladevelopers.filmfinder.pogo.Actor;
import com.kangladevelopers.filmfinder.pogo.Director;

import retrofit2.Call;
import retrofit2.http.GET;
//http://beta.json-generator.com/api/json/get/E1ddOCh4b

public interface DirectorAPI {
    @GET("E1ddOCh4b")
    Call<Director> getDirectorImageUrl(String director);

    @GET("E1ddOCh4b")
    Call<Director> getTestDirectorImageUrl();
}
