package com.art4muslim.orders.remote

import com.art4muslim.orders.remote.request.LoginRequest
import com.art4muslim.orders.remote.response.LoginResponse
import com.art4muslim.orders.remote.response.OrderResponse
import com.art4muslim.orders.remote.response.RestaurantResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by µðšţãƒâ ™ on 10/12/2019.
 *  ->
 */
interface RetrofitService {
    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("getResturants")
    fun getRestaurants(@Query("langu") lang: String): Call<RestaurantResponse>

    @POST("getOrder")
    fun getOrder(@Query("restId") restaurantId: String, @Query("langu") lang: String): Call<OrderResponse>
}