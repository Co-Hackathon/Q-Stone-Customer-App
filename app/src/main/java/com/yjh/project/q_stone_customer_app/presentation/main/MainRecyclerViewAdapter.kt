package com.yjh.project.q_stone_customer_app.presentation.main

import android.os.Handler
import android.os.Looper
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.estimote.sdk.Beacon


class MainRecyclerViewAdapter : RecyclerView.Adapter<MainViewHolder>(){

    var beacons = arrayListOf<com.yjh.project.q_stone_customer_app.domain.Beacon>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder = MainViewHolder(parent)

    override fun getItemCount(): Int = beacons.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        holder.apply {
            bindTo("abc",beacons[position])
        }
    }

    fun setData(beacons : ArrayList<com.yjh.project.q_stone_customer_app.domain.Beacon>){
        this.beacons=beacons
        notifyDataSetChanged()
    }

    fun updateList(beacons: ArrayList<com.yjh.project.q_stone_customer_app.domain.Beacon>) {
        var  callback =  Diff(this.beacons, beacons);
        val diffResult = DiffUtil.calculateDiff(callback)

        this.beacons.clear()
        this.beacons.addAll(beacons)

         Handler(Looper.getMainLooper()).post({
                diffResult.dispatchUpdatesTo(this)
         })
    }
}