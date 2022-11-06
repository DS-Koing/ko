package com.example.koing

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface S_MenuRetrofitInterface {
    @get:GET("smenu")
    val user: Call<S_Menu?>?

    @POST("save-smenu")
    fun saveUser(@Body jsonUser: S_Menu?): Call<ResponseBody?>?

}