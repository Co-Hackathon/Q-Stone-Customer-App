package com.yjh.project.q_stone_customer_app.presentation.OrderList

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.yjh.project.q_stone_customer_app.domain.Food


class OrderRecyclerViewAdapter : RecyclerView.Adapter<OrderViewHolder>(){

    var foods = arrayListOf<Food>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = OrderViewHolder(parent)

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.apply {
            bindTo(foods[position].iamge,foods[position].menuPrice,foods[position].menuName,{
                foods.remove(foods[position])
            })
        }
    }


    override fun getItemCount(): Int = foods.size

    fun setData(items : ArrayList<Food>){
        this.foods=items
        notifyDataSetChanged()
    }

}