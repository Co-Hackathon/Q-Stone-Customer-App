package com.yjh.project.q_stone_customer_app.presentation.main

import android.app.Activity
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v4.view.ViewCompat
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.estimote.sdk.Beacon
import com.yjh.project.q_stone_customer_app.presentation.DetailMarket.DetailActivity


class MainRecyclerViewAdapter(val activity: Activity) : RecyclerView.Adapter<MainViewHolder>(){

    var beacons = arrayListOf<com.yjh.project.q_stone_customer_app.domain.Beacon>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder = MainViewHolder(parent)

    override fun getItemCount(): Int = beacons.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        holder.apply {
            bindTo("abc",beacons[position],{
                startActivity(holder,Intent(activity,DetailActivity::class.java).apply {
                    putExtra("uuID",beacons[position].uuID)
                    putExtra("major",beacons[position].major)
                    putExtra("minor",beacons[position].minor)
                })
            })

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
    private fun startActivity(viewHolder: MainViewHolder, intent: Intent) {
        //activity animations
        var pairs = arrayOf<Pair<View, String>>(
        (
                Pair(viewHolder.beaconNameTextView, ViewCompat.getTransitionName(viewHolder.beaconNameTextView)))
                , Pair(viewHolder.beaconMacAdressTextView, ViewCompat.getTransitionName(viewHolder.beaconMacAdressTextView)))




        var options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,*pairs)
        activity.startActivity(intent, options.toBundle())
    }
}