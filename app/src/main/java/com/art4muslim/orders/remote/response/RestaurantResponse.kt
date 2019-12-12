package com.art4muslim.orders.remote.response

import com.art4muslim.orders.model.Restaurant
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by µðšţãƒâ ™ on 10/12/2019.
 *  ->
 */
class RestaurantResponse: StatusResponse() {
    @SerializedName("return")
    @Expose
    val data: MutableList<Restaurant>? = null
}