package com.yjh.project.q_stone_customer_app.network

import com.yjh.project.q_stone_customer_app.domain.Food
import com.yjh.project.q_stone_customer_app.domain.Market
import io.reactivex.Single
import retrofit2.Call
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("/userLogin/{id}/{password}")
    fun login(@Path("id")id : String,@Path("password")password: String) : Call<Void>

    @GET("/marketBecon/{uuID}/{major}/{minor}")
    fun loadMarket(@Path("uuID")uID : String,@Path("major") major : Int, @Path("minor") minor : Int) : Single<Result<Array<Market>>>

    @GET("/marketMenu/{categoryNum}")
    fun loadFood(@Path("categoryNum")  categoryNum : String) : Single<Result<ArrayList<Food>>>
}
