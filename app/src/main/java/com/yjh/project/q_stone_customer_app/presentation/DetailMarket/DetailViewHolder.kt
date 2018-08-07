package com.yjh.project.q_stone_customer_app.presentation.DetailMarket

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.yjh.project.q_stone_customer_app.R


class DetailViewHolder(val parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_detail, parent, false)
){
    var imageView = itemView.findViewById<ImageView>(R.id.image)
    var priceTexView = itemView.findViewById<TextView>(R.id.price)
    var nameTextView = itemView.findViewById<TextView>(R.id.name)

    fun  bindTo(image : String,price : String,name : String,onClick: (Any) -> Unit){
        priceTexView.text=price
        nameTextView.text=name
        itemView.setOnClickListener(onClick)
        Picasso.with(parent.context).load(image).into(imageView)
    }
}