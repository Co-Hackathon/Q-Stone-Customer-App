package com.yjh.project.q_stone_customer_app.presentation.main

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.estimote.sdk.BeaconManager
import com.estimote.sdk.Region
import com.yjh.project.q_stone_customer_app.R
import com.yjh.project.q_stone_customer_app.presentation.app.AppContract
import com.estimote.sdk.SystemRequirementsChecker
import com.yjh.project.q_stone_customer_app.di.app.App
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), MainContract.View {

    lateinit var beaconManager: BeaconManager
    lateinit var region: Region
    lateinit var mainPresenter: MainPresenter

    init {
        mainPresenter= MainPresenter(this)
        region = Region(
                            "paycheck region",
                            UUID.fromString("fda50693-a4e2-4fb1-afcf-c6eb07647825"),
                        205,
                        70
                        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        beaconInit()
        mainPresenter.beaconRanging(beaconManager)
    }

    override fun beaconInit() {
        beaconManager= BeaconManager(this)
    }

    override fun showDialog(str: String) {
        val dialog = AlertDialog.Builder(this@MainActivity)
        dialog.setTitle(str)
                .setMessage("비콘이 연결되었습니다.")
                .setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which -> }).create().show()
    }

    override fun onResume() {
        super.onResume()
        SystemRequirementsChecker.checkWithDefaultDialogs(this)

        beaconManager.connect {
            beaconManager.startRanging(
                   region
            )
        }
    }

    override fun showToast(str: String) {
        Toast.makeText(applicationContext,str,Toast.LENGTH_SHORT).show()
    }

    override fun setText(str: String) {
        text.text=str+"dd"
    }

    override fun onPause() {
        super.onPause()
        //beaconManager.stopMonitoring(region)
    }


}
