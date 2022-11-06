package com.example.koing

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CartRetrofitInterface {
    @get:GET("cart")
    val user: Call<Order?>?

    @POST("save-menu")
    fun saveUser(@Body jsonUser: Order?): Call<ResponseBody?>?
}