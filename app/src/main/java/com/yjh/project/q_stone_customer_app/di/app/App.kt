package com.yjh.project.q_stone_customer_app.di.app

import android.app.*
import android.content.Context
import android.content.Intent
import android.util.Log
import com.estimote.sdk.BeaconManager
import com.estimote.sdk.EstimoteSDK
import com.yjh.project.commitprogress.di.component.AppComponent
import com.yjh.project.commitprogress.di.component.DaggerAppComponent
import com.yjh.project.commitprogress.di.module.NetworkModule
import com.yjh.project.q_stone_customer_app.di.module.AppModule
import com.yjh.project.q_stone_customer_app.presentation.app.AppContract
import com.yjh.project.q_stone_customer_app.presentation.app.AppPresenter
import com.yjh.project.q_stone_customer_app.presentation.main.MainActivity


class App : Application(), AppContract.View {


    lateinit var appPresenter: AppPresenter
    lateinit var beaconManager: BeaconManager

    init {
        appPresenter = AppPresenter(this)
    }

    companion object {
        //platformStatic allow access it from java code
        @JvmStatic
        lateinit var component: AppComponent
        val BASE_URL = "http://192.168.0.139:8000"
    }

    override fun onCreate() {
        super.onCreate()
        beaconInit()

        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule(BASE_URL))
                .build()

      /*  appPresenter.beaconConnect(beaconManager)
        appPresenter.beaconMonitoring(beaconManager)*/

        Log.d("onCreate","test")
    }

    override fun beaconInit() {
        beaconManager = BeaconManager(this)
    }

    override fun isAlreadyRunActivity(): Boolean {
        var activity_manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        var task_Info = activity_manager.getRunningTasks(9999)

        var activityName: String

        for (i in 0 until task_Info.size) {

            activityName = task_Info[i].topActivity.packageName

            if (activityName.startsWith("com.yjh.project.q_stone_customer_app.di.app")) {
                return true
            }

        }
        return false
    }

    override fun startActivty() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra("executeType", "beacon")

        applicationContext.startActivity(intent)
    }

    override fun showNotification(str : String) {
        var notifyIntent =  Intent(this, MainActivity::class.java)
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)

        var pendingIntent = PendingIntent.getActivities (this, 0, arrayOf(notifyIntent) , PendingIntent.FLAG_UPDATE_CURRENT)

        var notification =  Notification.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(str)
                .setTicker("비콘")
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .build()

        var notificationManager = getSystemService (Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification)
    }
}