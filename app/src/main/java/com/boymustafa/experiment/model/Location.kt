package com.boymustafa.experiment.model

import com.google.gson.annotations.SerializedName

import java.io.Serializable

/*
Model to store location data
 */

class Location : Serializable {

    @SerializedName("lat")
    public val lat: Double? = null
    @SerializedName("lng")
    public val lng: Double? = null
    @SerializedName("address")
    public val address: String? = null

}
