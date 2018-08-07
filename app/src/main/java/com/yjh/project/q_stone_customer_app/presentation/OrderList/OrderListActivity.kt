package com.yjh.project.q_stone_customer_app.presentation.OrderList

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.yjh.project.q_stone_customer_app.R
import com.yjh.project.q_stone_customer_app.domain.Food
import kotlinx.android.synthetic.main.activity_order_list.*

class OrderListActivity : Activity() {

    lateinit var orderRecyclerViewAdapter: OrderRecyclerViewAdapter
    var foods = arrayListOf<Food>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)

        orderRecyclerViewAdapter=OrderRecyclerViewAdapter()

        foods=intent.getParcelableArrayListExtra("array")

        init()
    }

    private fun init(){
        order_recycler_view.adapter=orderRecyclerViewAdapter
        order_recycler_view.layoutManager=LinearLayoutManager(applicationContext)
        orderRecyclerViewAdapter.setData(foods)

        back_button.setOnClickListener {

        }
    }

}
