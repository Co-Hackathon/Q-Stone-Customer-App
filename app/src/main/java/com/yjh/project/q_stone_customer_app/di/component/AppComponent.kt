package com.yjh.project.commitprogress.di.component

import com.yjh.project.commitprogress.di.module.NetworkModule
import com.yjh.project.q_stone_customer_app.di.app.App
import com.yjh.project.q_stone_customer_app.di.module.AppModule
import com.yjh.project.q_stone_customer_app.presentation.intro.Login
import com.yjh.project.q_stone_customer_app.presentation.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [(AppModule::class),(NetworkModule::class)]
)
interface AppComponent{

    fun inject(app: App)

    fun inject(mainActivity: MainActivity)
    fun inject(login: Login)

}