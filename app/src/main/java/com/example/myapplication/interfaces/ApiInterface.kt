package com.example.myapplication.interfaces

import com.example.myapplication.model.AllUserResponse
import com.example.myapplication.model.LoginDataPost
import com.example.myapplication.model.LoginResponse
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
    @Headers("Content-Type: application/json")
    @POST("auth/login")
    suspend fun login(@Body body: LoginDataPost ): Response<LoginResponse>

    @Headers("Content-Type: application/json")
    @GET("user/all")
    suspend fun getUsers(@Header("x-access-token") token: String): Response<AllUserResponse>
}