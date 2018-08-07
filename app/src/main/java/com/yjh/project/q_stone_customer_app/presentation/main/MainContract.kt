package com.yjh.project.q_stone_customer_app.presentation.main

import com.estimote.sdk.BeaconManager

interface MainContract{

    interface View{
        fun beaconInit()
        fun showDialog(str: String)
        fun showToast(str : String)
        fun setText(str : String)
    }

    interface UserActionListener{
        fun beaconRanging(beaconManager: BeaconManager)
    }
}