package com.yjh.project.q_stone_customer_app.domain

import com.google.gson.annotations.SerializedName


data class Market(
        @SerializedName(" ") var marketBeacon : String,
        @SerializedName("") var marketName : String,
        @SerializedName("") var marketX : String,
        @SerializedName("") var marketY : String,
        @SerializedName("") var category: Int
)