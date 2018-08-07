package com.yjh.project.q_stone_customer_app.presentation.main


import android.util.Log
import com.estimote.sdk.Beacon
import com.estimote.sdk.BeaconManager
import com.estimote.sdk.Region
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailPresenter(val view : DetailContract.View) : DetailContract.UserActionListener {


    var check : Boolean =false

    override fun beaconRanging(beaconManager: BeaconManager) {
        beaconManager.setRangingListener(object  : BeaconManager.RangingListener{
            override fun onBeaconsDiscovered(p0: Region?, p1: MutableList<Beacon>?) {
                if(!p1!!.isEmpty()) {
                    Log.d("beacon","test")
                    var beacon = p1!![0]
                    view.setText(beacon.rssi.toString(),"신호양호")

                    if(p1!![0].rssi < -90){
                        view.setText(beacon.rssi.toString(),"신호 불안정")
                    }else if(p1!![0].rssi < -100){
                        view.setText(beacon.rssi.toString(),"신호 종료")
                    }
                    if (!check){
                        view.showDialog("연결 성공")
                        view.EndLoading()
                        view.getMarket()
                        check=true
                    }
                }else{
                    if(check){
                        view.showDialog("연결 종료")
                        view.setLoading()
                        check=false
                    }
                }
            }

        })
    }

}