package com.art4muslim.orders.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by µðšţãƒâ ™ on 10/12/2019.
 *  ->
 */
class RetrofitWebService private constructor() {

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .writeTimeout(10, TimeUnit.MINUTES)
            .readTimeout(10, TimeUnit.MINUTES)
            .addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mServices[url] = retrofit.create(RetrofitService::class.java)
    }

    companion object {
        private val mServices = HashMap<String, RetrofitService>()
        private const val url = "http://talabat.art4muslim.net/api/"

        val service: RetrofitService
            get() {
                if (null == mServices[url]) {
                    RetrofitWebService()
                }
                return mServices[url] as RetrofitService
            }
    }
}