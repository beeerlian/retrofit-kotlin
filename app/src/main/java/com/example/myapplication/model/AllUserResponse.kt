package com.example.myapplication.model

import com.google.gson.annotations.SerializedName


data class AllUserResponse (

    @SerializedName("success" ) var success : Boolean?        = null,
    @SerializedName("data"    ) var data    : ArrayList<Data> = arrayListOf()

)

data class User (

    @SerializedName("id"           ) var id           : String?           = null,
    @SerializedName("fcm"          ) var fcm          : String?           = null,
    @SerializedName("lastLoggedIn" ) var lastLoggedIn : String?           = null,
    @SerializedName("connection"   ) var connection   : ArrayList<String> = arrayListOf(),
    @SerializedName("email"        ) var email        : String?           = null,
    @SerializedName("username"     ) var username     : String?           = null,
    @SerializedName("password"     ) var password     : String?           = null,
    @SerializedName("role"         ) var role         : String?           = null

)


data class Data (

    @SerializedName("id"   ) var id   : String? = null,
    @SerializedName("user" ) var user : User?   = User()

)
