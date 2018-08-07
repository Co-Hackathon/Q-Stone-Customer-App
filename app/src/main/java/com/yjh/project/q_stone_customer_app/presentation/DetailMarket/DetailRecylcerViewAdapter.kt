package com.yjh.project.q_stone_customer_app.presentation.DetailMarket

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.yjh.project.q_stone_customer_app.domain.Food


class DetailRecylcerViewAdapter : RecyclerView.Adapter<DetailViewHolder>(){

    var foods = arrayListOf<Food>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder = DetailViewHolder(parent)

    override fun getItemCount(): Int = foods.size


    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.apply {
            bindTo(foods[position].iamge,foods[position].menuPrice,foods[position].menuName,{

            })
        }
    }

     fun setData(items : ArrayList<Food>){
        this.foods=items
         notifyDataSetChanged()
    }



}