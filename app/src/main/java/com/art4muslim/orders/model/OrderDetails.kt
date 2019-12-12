package com.art4muslim.orders.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by µðšţãƒâ ™ on 10/12/2019.
 *  ->
 */
data class OrderDetails(
    @SerializedName("prod_name")
    @Expose
    val name: String,
    @SerializedName("prod_quantity")
    @Expose
    val quantity: String,
    @SerializedName("prod_price")
    @Expose
    val price: String,
    @SerializedName("prod_image")
    @Expose
    val image: String
) : Serializable