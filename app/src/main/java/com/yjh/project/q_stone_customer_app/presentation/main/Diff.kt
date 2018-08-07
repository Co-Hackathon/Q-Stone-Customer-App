package com.yjh.project.q_stone_customer_app.presentation.main

import android.support.v7.util.DiffUtil
import com.yjh.project.q_stone_customer_app.domain.Beacon

class Diff(val oldItem : ArrayList<Beacon> , var newItem : ArrayList<Beacon>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getOldListSize(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getNewListSize(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}