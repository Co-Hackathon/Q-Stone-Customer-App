package com.yjh.project.q_stone_customer_app.domain

import com.google.gson.annotations.SerializedName


data class User(
        @SerializedName(" ") var userName : String,
        @SerializedName("") var userGender : String,
        @SerializedName("") var userID : String,
        @SerializedName("") var userPassword : String
)