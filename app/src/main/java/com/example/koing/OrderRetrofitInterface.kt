package com.example.koing

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface OrderRetrofitInterface {
    @get:GET("order")
    val user: Call<Order?>?

    @POST("save-order")
    fun saveUser(@Body jsonUser: Order?): Call<ResponseBody?>?
}