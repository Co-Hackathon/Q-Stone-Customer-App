package com.yjh.project.q_stone_customer_app.di.app

import android.app.Application
import com.yjh.project.commitprogress.di.component.AppComponent
import com.yjh.project.commitprogress.di.component.DaggerAppComponent
import com.yjh.project.commitprogress.di.module.NetworkModule
import com.yjh.project.q_stone_customer_app.di.module.AppModule


class App : Application(){
    companion object {
        //platformStatic allow access it from java code
        @JvmStatic lateinit var component: AppComponent
        val BASE_URL="https://api.github.com/"
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule(BASE_URL))
                .build()
    }
}