package com.art4muslim.orders.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by µðšţãƒâ ™ on 10/12/2019.
 *  ->
 */
data class Restaurant(
    @SerializedName("rest_id")
    @Expose
    val id: String,
    @SerializedName("rest_name")
    @Expose
    val name: String,
    @SerializedName("rest_img")
    @Expose
    val image: String,
    @SerializedName("rest_location")
    @Expose
    val location: String,
    @SerializedName("rest_type")
    @Expose
    val type: String
)