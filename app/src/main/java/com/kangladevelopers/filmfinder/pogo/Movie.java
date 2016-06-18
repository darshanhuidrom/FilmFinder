
package com.kangladevelopers.filmfinder.pogo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("starring")
    @Expose
    private String starring;
    @SerializedName("director")
    @Expose
    private String director;
    @SerializedName("runtime")
    @Expose
    private Integer runtime;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("synopsis")
    @Expose
    private String synopsis;
    @SerializedName("imdb")
    @Expose
    private Integer imdb;
    @SerializedName("rotten_tometo")
    @Expose
    private Integer rottenTometo;
    @SerializedName("budget")
    @Expose
    private Integer budget;
    @SerializedName("box_office")
    @Expose
    private Integer boxOffice;
    @SerializedName("pic_url")
    @Expose
    private String picUrl;
    @SerializedName("video_url")
    @Expose
    private String videoUrl;
    @SerializedName("year")
    @Expose
    private Integer year;

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * 
     * @param country
     *     The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The starring
     */
    public String getStarring() {
        return starring;
    }

    /**
     * 
     * @param starring
     *     The starring
     */
    public void setStarring(String starring) {
        this.starring = starring;
    }

    /**
     * 
     * @return
     *     The director
     */
    public String getDirector() {
        return director;
    }

    /**
     * 
     * @param director
     *     The director
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * 
     * @return
     *     The runtime
     */
    public Integer getRuntime() {
        return runtime;
    }

    /**
     * 
     * @param runtime
     *     The runtime
     */
    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    /**
     * 
     * @return
     *     The releaseDate
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * 
     * @param releaseDate
     *     The release_date
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * 
     * @return
     *     The synopsis
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * 
     * @param synopsis
     *     The synopsis
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * 
     * @return
     *     The imdb
     */
    public Integer getImdb() {
        return imdb;
    }

    /**
     * 
     * @param imdb
     *     The imdb
     */
    public void setImdb(Integer imdb) {
        this.imdb = imdb;
    }

    /**
     * 
     * @return
     *     The rottenTometo
     */
    public Integer getRottenTometo() {
        return rottenTometo;
    }

    /**
     * 
     * @param rottenTometo
     *     The rotten_tometo
     */
    public void setRottenTometo(Integer rottenTometo) {
        this.rottenTometo = rottenTometo;
    }

    /**
     * 
     * @return
     *     The budget
     */
    public Integer getBudget() {
        return budget;
    }

    /**
     * 
     * @param budget
     *     The budget
     */
    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    /**
     * 
     * @return
     *     The boxOffice
     */
    public Integer getBoxOffice() {
        return boxOffice;
    }

    /**
     * 
     * @param boxOffice
     *     The box_office
     */
    public void setBoxOffice(Integer boxOffice) {
        this.boxOffice = boxOffice;
    }

    /**
     * 
     * @return
     *     The picUrl
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * 
     * @param picUrl
     *     The pic_url
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * 
     * @return
     *     The videoUrl
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * 
     * @param videoUrl
     *     The video_url
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    /**
     * 
     * @return
     *     The year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * 
     * @param year
     *     The year
     */
    public void setYear(Integer year) {
        this.year = year;
    }

}
