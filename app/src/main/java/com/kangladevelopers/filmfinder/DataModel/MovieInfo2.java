package com.kangladevelopers.filmfinder.DataModel;

import java.io.Serializable;

/**
 * Created by HUIDROM on 6/12/2016.
 */
public class MovieInfo2 implements Serializable{

    private String name;
    private String country;
    private String type;
    private String starring;
    private String director;
    private int runtime;
    private String release_date;
    private int imdb;
    private String synopsis;
    private int rotten_tometo;
    private int budget;
    private String box_office;
    private String pic_url;
    private String video_url;
    private int year;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStarring() {
        return starring;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }

    public String getDirector() {
        return director;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getImdb() {
        return imdb;
    }

    public void setImdb(int imdb) {
        this.imdb = imdb;
    }

    public int getRotten_tometo() {
        return rotten_tometo;
    }

    public void setRotten_tometo(int rotten_tometo) {
        this.rotten_tometo = rotten_tometo;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getBox_office() {
        return box_office;
    }

    public void setBox_office(String box_office) {
        this.box_office = box_office;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
