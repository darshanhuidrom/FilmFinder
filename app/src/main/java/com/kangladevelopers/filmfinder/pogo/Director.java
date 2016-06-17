
package com.kangladevelopers.filmfinder.pogo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Director {

    @SerializedName("career_status")
    @Expose
    private String careerStatus;
    @SerializedName("marital_status")
    @Expose
    private String maritalStatus;
    @SerializedName("spouse")
    @Expose
    private String spouse;
    @SerializedName("synopsis")
    @Expose
    private String synopsis;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("director_name")
    @Expose
    private String directorName;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;

    /**
     * 
     * @return
     *     The careerStatus
     */
    public String getCareerStatus() {
        return careerStatus;
    }

    /**
     * 
     * @param careerStatus
     *     The career_status
     */
    public void setCareerStatus(String careerStatus) {
        this.careerStatus = careerStatus;
    }

    /**
     * 
     * @return
     *     The maritalStatus
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * 
     * @param maritalStatus
     *     The marital_status
     */
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * 
     * @return
     *     The spouse
     */
    public String getSpouse() {
        return spouse;
    }

    /**
     * 
     * @param spouse
     *     The spouse
     */
    public void setSpouse(String spouse) {
        this.spouse = spouse;
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
     *     The age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 
     * @param age
     *     The age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 
     * @return
     *     The directorName
     */
    public String getDirectorName() {
        return directorName;
    }

    /**
     * 
     * @param directorName
     *     The director_name
     */
    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    /**
     * 
     * @return
     *     The imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 
     * @param imageUrl
     *     The image_url
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
