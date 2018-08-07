package com.yjh.project.q_stone_customer_app.presentation.app

import android.util.Log
import com.estimote.sdk.Beacon
import com.estimote.sdk.BeaconManager
import com.estimote.sdk.Region
import java.util.*
import com.estimote.sdk.EstimoteSDK.getApplicationContext
import android.content.Intent
import com.yjh.project.q_stone_customer_app.presentation.main.MainActivity


class AppPresenter(val view : AppContract.View) : AppContract.UserActionListener {
    override fun beaconConnect(beaconManager: BeaconManager,region: Region) {
        beaconManager.connect(object : BeaconManager.ServiceReadyCallback{
            override fun onServiceReady() {
                beaconManager.startMonitoring(
                        region
                )
            }
        })
    }

    override fun beaconMonitoring(beaconManager: BeaconManager) {
        beaconManager.setMonitoringListener(object : BeaconManager.MonitoringListener{
            override fun onExitedRegion(p0: Region?) {
                Log.d("Log","비콘나감")
                //비콘이랑 연결해제
                view.showNotification(" 연결 나감 ")

            }

            override fun onEnteredRegion(p0: Region?, p1: MutableList<Beacon>?) {
                view.showNotification("비콘"+ p1!!.get(0).getRssi())

/*
                if(!view.isAlreadyRunActivity()){

                }else{
                    view.showNotification("맥도날드 비콘 연결 ")
                }*/
                //iew.startActivty()

            }

        })
    }

}