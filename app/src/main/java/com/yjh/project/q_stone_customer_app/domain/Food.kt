package com.yjh.project.q_stone_customer_app.domain

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Food(
        @SerializedName("menuNo") var menuNum :Int,
        @SerializedName("market") var marektNum : Int,
        @SerializedName("menuName") var menuName : String,
        @SerializedName("menuURL") var iamge : String,
        @SerializedName("menuPrice") var  menuPrice: String,
        var onClick : Boolean =false
) : Parcelable