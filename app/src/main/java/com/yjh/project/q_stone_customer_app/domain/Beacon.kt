package com.yjh.project.q_stone_customer_app.domain

import com.estimote.sdk.MacAddress

data class Beacon(
        var  uuID : String,
        var macAddress: String,
        var name : String,
        var major : Int,
        var minor : Int
)