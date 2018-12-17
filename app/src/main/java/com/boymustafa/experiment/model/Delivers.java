package com.boymustafa.experiment.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/*
Model to store data from API
 */

public class Delivers implements Serializable {

    @SerializedName("id")
    public Integer id;
    @SerializedName("description")
    public String description;
    @SerializedName("imageUrl")
    public String imageUrl;
    @SerializedName("location")
    public Location location;
}
