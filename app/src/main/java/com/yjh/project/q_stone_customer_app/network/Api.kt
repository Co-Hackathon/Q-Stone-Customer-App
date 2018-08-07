package com.yjh.project.q_stone_customer_app.network

import io.reactivex.Single
import retrofit2.Call
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("/userLogin/{id}/{password}")
    fun login(@Path("id")id : String,@Path("password")password: String) : Call<Void>
}