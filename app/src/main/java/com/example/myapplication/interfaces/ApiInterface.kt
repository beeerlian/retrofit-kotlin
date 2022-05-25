package com.example.myapplication.interfaces

import com.example.myapplication.model.LoginDataPost
import com.example.myapplication.model.LoginResponse
import okhttp3.RequestBody
import retrofit2.Response

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {
    @Headers("Content-Type: application/json")
    @POST("auth/login")
    suspend fun login(@Body body: LoginDataPost ): Response<LoginResponse>
}