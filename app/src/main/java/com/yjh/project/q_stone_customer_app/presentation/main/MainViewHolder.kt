package com.yjh.project.q_stone_customer_app.presentation.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.estimote.sdk.Beacon
import com.yjh.project.q_stone_customer_app.R

class MainViewHolder(
        val parent: ViewGroup
) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_beacon, parent, false)){


    var beaconNameTextView = itemView.findViewById<TextView>(R.id.beacon_title)
    var beaconMacAdressTextView = itemView.findViewById<TextView>(R.id.beacon_mac_address)

    fun  bindTo(image : String, beacon: com.yjh.project.q_stone_customer_app.domain.Beacon){
        beaconNameTextView.text="맥도날드 비콘"
        beaconMacAdressTextView.text=beacon.macAddress
    }
}
