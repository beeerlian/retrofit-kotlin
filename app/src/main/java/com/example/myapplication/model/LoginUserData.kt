package com.example.myapplication.model

import com.google.gson.annotations.SerializedName


data class LoginUserData (

    @SerializedName("id"           ) var id           : String?           = null,
    @SerializedName("username"     ) var username     : String?           = null,
    @SerializedName("email"        ) var email        : String?           = null,
    @SerializedName("password"     ) var password     : String?           = null,
    @SerializedName("role"         ) var role         : String?           = null,
    @SerializedName("fcm"          ) var fcm          : String?           = null,
    @SerializedName("lastLoggedIn" ) var lastLoggedIn : String?              = null,
    @SerializedName("connection"   ) var connection   : ArrayList<String> = arrayListOf(),
    @SerializedName("tokenExpired" ) var tokenExpired : String?              = null,
    @SerializedName("accessToken"  ) var accessToken  : String?           = null

)
