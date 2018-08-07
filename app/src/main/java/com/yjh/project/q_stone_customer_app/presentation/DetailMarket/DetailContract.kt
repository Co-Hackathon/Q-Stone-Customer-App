package com.yjh.project.q_stone_customer_app.presentation.main

import com.estimote.sdk.BeaconManager
import com.yjh.project.q_stone_customer_app.domain.Beacon
import com.yjh.project.q_stone_customer_app.domain.Market
import com.yjh.project.q_stone_customer_app.network.Api

interface DetailContract{

    interface View{
        fun beaconInit()
        fun showDialog(str: String)
        fun showToast(str : String)
        fun setText(str : String,check :String)
        fun setLoading()
        fun EndLoading()
        fun getMarket()
    }

    interface UserActionListener{
        fun beaconRanging(beaconManager: BeaconManager)
    }


}