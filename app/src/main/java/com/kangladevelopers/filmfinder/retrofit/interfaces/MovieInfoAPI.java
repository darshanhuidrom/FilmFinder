package com.kangladevelopers.filmfinder.retrofit.interfaces;

import com.kangladevelopers.filmfinder.DataModel.MovieInfo2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
// https://futurestud.io/blog/retrofit-multiple-query-parameters-of-same-name


/**
 * Created by HUIDROM on 6/12/2016.
 */
public interface MovieInfoAPI {
    @GET("/4JV3Fj8VZ")
    Call<List<MovieInfo2>> getMovies(
            @Query("actors") String actors
    );

    Call<List<MovieInfo2>> getMovies(
            @Query("actors") String actors,
            @Query("directors") String directors
    );
    Call<List<MovieInfo2>> getMovies(
            @Query("actors") String actors,
            @Query("directors") String directors,
            @Query(("type")) String types
    );


}
