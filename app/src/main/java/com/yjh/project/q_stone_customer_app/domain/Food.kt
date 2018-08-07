package com.yjh.project.q_stone_customer_app.domain

import com.google.gson.annotations.SerializedName

data class Food(
        @SerializedName("menuNo") var menuNum :Int,
        @SerializedName("market") var marektNum : Int,
        @SerializedName("menuName") var menuName : String,
        @SerializedName("menuURL") var iamge : String,
        @SerializedName("menuPrice") var  menuPrice: String

)