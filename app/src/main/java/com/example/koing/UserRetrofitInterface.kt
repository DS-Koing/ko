package com.example.koing

import retrofit2.http.GET
import retrofit2.http.POST
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body

interface UserRetrofitInterface {
    @get:GET("user")
    val user: Call<User?>?

    @POST("save-user")
    fun saveUser(@Body jsonUser: User?): Call<ResponseBody?>?
}