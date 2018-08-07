package com.yjh.project.q_stone_customer_app.presentation.app

import com.estimote.sdk.BeaconManager

interface AppContract{

    interface View{
        fun beaconInit()
        fun isAlreadyRunActivity() : Boolean
        fun showNotification(str : String)
        fun startActivty()
    }

    interface UserActionListener{
        fun beaconConnect(beaconManager: BeaconManager)
        fun beaconMonitoring(beaconManager: BeaconManager)
    }
}