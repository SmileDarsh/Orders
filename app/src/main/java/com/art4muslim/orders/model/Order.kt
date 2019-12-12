package com.art4muslim.orders.model

import com.google.android.gms.maps.model.Marker
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by µðšţãƒâ ™ on 10/12/2019.
 *  ->
 */
data class Order(
    @SerializedName("order_id")
    @Expose
    val id: String,
    @SerializedName("order_user")
    @Expose
    val userName: String,
    @SerializedName("order_price")
    @Expose
    val price: String,
    @SerializedName("user_location")
    @Expose
    val location: String,
    @SerializedName("resturant_name")
    @Expose
    val restaurantName: String,
    @SerializedName("order_details")
    @Expose
    val orderDetails: MutableList<OrderDetails>,
    var marker: Marker
) : Serializable