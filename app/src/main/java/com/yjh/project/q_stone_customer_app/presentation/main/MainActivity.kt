package com.yjh.project.q_stone_customer_app.presentation.main

import android.bluetooth.BluetoothAdapter
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.Toast
import com.estimote.sdk.Beacon
import com.estimote.sdk.BeaconManager
import com.estimote.sdk.Region
import com.yjh.project.q_stone_customer_app.R
import com.yjh.project.q_stone_customer_app.presentation.app.AppContract
import com.estimote.sdk.SystemRequirementsChecker
import com.estimote.sdk.cloud.model.BeaconInfo
import com.estimote.sdk.service.internal.BluetoothScanner
import com.estimote.sdk.service.internal.BluetoothScannerAdapter
import com.yjh.project.q_stone_customer_app.di.app.App
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.bluetooth.BluetoothDevice
import android.util.Log
import kotlin.experimental.and
import android.bluetooth.BluetoothManager
import android.content.Context
import android.os.Handler
import android.support.annotation.UiThread
import com.estimote.sdk.repackaged.dfu_v0_6_1.no.nordicsemi.android.dfu.internal.exception.UnknownResponseException.bytesToHex


class MainActivity : AppCompatActivity(), MainContract.View  {

    lateinit var beaconManager: BeaconManager
    lateinit var region: Region
    lateinit var mainPresenter: MainPresenter
    val mainRecyclerViewAdapter: MainRecyclerViewAdapter by lazy { MainRecyclerViewAdapter() }

    var btManager: BluetoothManager? = null
    var btAdapter: BluetoothAdapter? = null
    var scanHandler = Handler()
    val scan_interval_ms = 5000L
    var isScanning = false


    var test= arrayListOf(com.yjh.project.q_stone_customer_app.domain.Beacon("fda50693-a4e2-4fb1-afcf-c6eb07647825","40:F3:85:90:63:5F","맥",205,70))


    init {
        mainPresenter = MainPresenter(this)
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
        btManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        btAdapter = btManager!!.adapter

        init()

        scanHandler.post(scanRunnable)

        this.runOnUiThread {
            mainRecyclerViewAdapter.setData(test)
        }

    }

    private fun init(){
        main_recycler_view.layoutManager = LinearLayoutManager(applicationContext)
        main_recycler_view.adapter = mainRecyclerViewAdapter


        mainRecyclerViewAdapter.setData(test)
    }

    override fun beaconInit() {
        beaconManager= BeaconManager(this)
    }

    override fun showDialog(str: String) {
        val dialog = AlertDialog.Builder(this@MainActivity)
        dialog.setTitle(str)
                .setMessage("비콘이 $str 되었습니다.")
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
    }

    override fun onPause() {
        super.onPause()
        beaconManager.stopMonitoring(region)
    }

    private val scanRunnable = object : Runnable {
        override fun run() {

            if (isScanning) {
                if (btAdapter != null) {
                    btAdapter!!.stopLeScan(leScanCallback)
                }
                swipe_layout.isRefreshing=true
            } else {
                if (btAdapter != null) {
                    btAdapter!!.startLeScan(leScanCallback)

                 /*   test.toTypedArray().let {
                        mainRecyclerViewAdapter.setData(it)
                    }*/
                }

                swipe_layout.isRefreshing=false

            }

            isScanning = !isScanning
            scanHandler.postDelayed(this, scan_interval_ms)


        }
    }

    private val leScanCallback = BluetoothAdapter.LeScanCallback { device, rssi, scanRecord ->
        var startByte = 2
        var patternFound = false
        while (startByte <= 5) {
            if (scanRecord[startByte + 2].toInt() and 0xff == 0x02 && //Identifies an iBeacon
                    scanRecord[startByte + 3].toInt() and 0xff == 0x15) { //Identifies correct data length
                patternFound = true
                break
            }
            startByte++
        }

        if (patternFound) {
            //Convert to hex String
            val uuidBytes = ByteArray(16)
            System.arraycopy(scanRecord, startByte + 4, uuidBytes, 0, 16)
            val hexString = bytesToHex(uuidBytes,0,uuidBytes.size)



            //UUID detection
            val uuid = hexString.substring(0, 8) + "-" +
                    hexString.substring(8, 12) + "-" +
                    hexString.substring(12, 16) + "-" +
                    hexString.substring(16, 20) + "-" +
                    hexString.substring(20, 32)

            // major
            val major = (scanRecord[startByte + 20] and 0xff.toByte()) * 0x100 + (scanRecord[startByte + 21] and 0xff.toByte())

            // minor
            val minor = (scanRecord[startByte + 22] and 0xff.toByte()) * 0x100 + (scanRecord[startByte + 23] and 0xff.toByte())

            Log.i("test", "UUID: $uuid\\nmajor: $major\\nminor$minor")
            //test.add(com.yjh.project.q_stone_customer_app.domain.Beacon(uuid,"40:F3:85:90:63:5F","홍콩",major,minor))
            Log.d("test",test.size.toString())

        }
    }

    /**
     * bytesToHex method
     */



}
