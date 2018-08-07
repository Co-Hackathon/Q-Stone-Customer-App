package com.yjh.project.q_stone_customer_app.presentation.main


import android.util.Log
import com.estimote.sdk.Beacon
import com.estimote.sdk.BeaconManager
import com.estimote.sdk.Region

class MainPresenter(val view : MainContract.View) : MainContract.UserActionListener {


    var check : Boolean =false

    override fun beaconRanging(beaconManager: BeaconManager) {
        beaconManager.setRangingListener(object  : BeaconManager.RangingListener{
            override fun onBeaconsDiscovered(p0: Region?, p1: MutableList<Beacon>?) {
                if(!p1!!.isEmpty()) {

                    Log.d("beacon","test")
                    var beacon = p1!![0]
                    view.setText(beacon.rssi.toString())

                    if(!check){
                        check=true
                        view.showDialog("연결 성공")
                    }

                }else{
                    if(check){
                        check=false
                        view.showDialog("연결 종료")
                    }
                }
            }

        })
    }

}