package com.example.koing

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MenuRetrofitInterface {
    @get:GET("menu")
    val user: Call<Menu?>?

    @POST("save-menu")
    fun saveUser(@Body jsonUser: Menu?): Call<ResponseBody?>?
}