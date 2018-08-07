package com.yjh.project.q_stone_customer_app.domain

import com.google.gson.annotations.SerializedName


data class Market(
        @SerializedName("marketBecon") var marketBeacon : String,
        @SerializedName("marketBeName") var marketBeName : String,
        @SerializedName("marketName") var marketName : String,
        var marketX : String,
        var marketY : String,
        @SerializedName("categoryNo") var category: Int,
        @SerializedName("marketURL") var imageUrl: String,
        var marketTime : String,
        var marketPhone : String,
        var marketAddress : String
)