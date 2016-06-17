package com.kangladevelopers.filmfinder.DataModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL PC on 2/16/2016.
 */
public class MoveInfo implements Serializable {
// http://beta.json-generator.com/api/json/get/N1AfbQIVb
    public String name;
    public String director;
    public String year;
    public String imageUrl;
    public String trailerUrl;
    public String boxOffice;

    public List<String> cast;
    public List<String> type;

    public String getName() {
        return name;
    }

    public MoveInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getDirector() {
        return director;
    }

    public MoveInfo setDirector(String director) {
        this.director = director;
        return this;
    }

    public String getYear() {
        return year;
    }

    public MoveInfo setYear(String year) {
        this.year = year;
        return  this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public MoveInfo setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public MoveInfo setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
        return this;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public MoveInfo setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
        return this;
    }

    public List<String> getCast() {
        return cast;
    }

    public MoveInfo setCast(List<String> cast) {
        this.cast = cast;
        return this;

    }

    public List<String> getType() {
        return type;
    }

    public MoveInfo setType(List<String> type) {
        this.type = type;
        return this;
    }
}
