package com.yjh.project.q_stone_customer_app.presentation.DetailMarket

import android.app.*
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.Fade
import android.transition.Slide
import android.view.View
import com.estimote.sdk.BeaconManager
import com.estimote.sdk.Region
import com.estimote.sdk.SystemRequirementsChecker
import com.squareup.picasso.Picasso
import com.yjh.project.q_stone_customer_app.R
import com.yjh.project.q_stone_customer_app.di.app.App
import com.yjh.project.q_stone_customer_app.domain.Market
import com.yjh.project.q_stone_customer_app.network.Api
import com.yjh.project.q_stone_customer_app.presentation.OrderList.OrderListActivity
import com.yjh.project.q_stone_customer_app.presentation.app.AppContract
import com.yjh.project.q_stone_customer_app.presentation.app.AppPresenter
import com.yjh.project.q_stone_customer_app.presentation.main.DetailContract
import com.yjh.project.q_stone_customer_app.presentation.main.DetailPresenter
import com.yjh.project.q_stone_customer_app.presentation.main.MainActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity(), DetailContract.View , AppContract.View{


    @Inject
    lateinit var api: Api

    lateinit var region: Region

    lateinit var beaconManager : BeaconManager

    lateinit var uID: String

    lateinit var presenter: DetailPresenter

    lateinit var market: Market
    lateinit var appPresente : AppPresenter

    lateinit var detailRecylcerViewAdapter: DetailRecylcerViewAdapter

    var major: Int = 0
    var minor: Int = 0


    init {

        appPresente = AppPresenter(this)
        presenter= DetailPresenter(this)

        detailRecylcerViewAdapter= DetailRecylcerViewAdapter()


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        App.component.inject(this)

        beaconInit()


        setWindowAnimations()

        uID=intent.getStringExtra("uuID")
        major=intent.getIntExtra("major",0)
        minor=intent.getIntExtra("minor",0)

        region = Region(
                "paycheck region",
                UUID.fromString(uID),
                major,
                minor
        )
        appPresente.beaconMonitoring(beaconManager)
        appPresente.beaconConnect(beaconManager,region)
        presenter.beaconRanging(beaconManager)

        detail_recycler_view.layoutManager = GridLayoutManager(applicationContext, 2)
        detail_recycler_view.adapter = detailRecylcerViewAdapter

        setLoading()
        loadMarket()

        order_button.setOnClickListener {
            Intent(this@DetailActivity,OrderListActivity::class.java).apply {

                var v=detailRecylcerViewAdapter.getData().filter{ it.onClick }

                putExtra("array",v.toTypedArray())
            }.let {
                startActivity(it)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        beaconManager.stopMonitoring(region)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        beaconManager.stopMonitoring(region)
        beaconManager.stopRanging(region)
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

    override fun EndLoading() {
        progress.visibility=View.GONE
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

    override fun showDialog(str: String) {
        val dialog = AlertDialog.Builder(this@DetailActivity)
        dialog.setTitle(str)
                .setMessage("비콘이 $str 되었습니다.")
                .setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which -> }).create().show()
    }


    override fun showToast(str: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setText(str: String,check :String) {
        beacon_num.text="rssi:"+str
        beacon_network.text=check
    }

    private fun setWindowAnimations(){
        //이동 애니메이션 구현
        val slideTransition =  Fade(3)

        slideTransition.duration=500L
        this.window.enterTransition=slideTransition
        this.window.exitTransition = slideTransition
    }

    override fun setLoading() {
        progress.visibility=View.VISIBLE
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

    override fun getMarket() {
        api.loadFood(market.category.toString()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it ->
                    it?.let {
                        if(it.response().code()==200){
                            detailRecylcerViewAdapter.setData(it.response().body())
                        }else if(it.response().code()==204){
                            showToast("해당 음식 정보가 없습니다")
                        }

                    }

                }
    }


    private fun loadMarket() {
        api.loadMarket(uID, major, minor).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { it ->

                            it?.let {
                                market=it.response().body()[0]
                                if(it.response().code()==200){
                                    market_name.text = it.response().body()[0].marketName
                                    market_beacon_name.text=it.response().body()[0].marketBeName
                                    market_address.text = it.response().body()[0].marketAddress
                                    Picasso.with(this@DetailActivity).load(it.response().body()[0].imageUrl).into(market_image)
                                    market_phone.text = "전화번호 "+it.response().body()[0].marketPhone
                                    market_time.text =it.response().body()[0].marketTime+" 운영"
                                    market_mac_address.text="UUID:"+uID
                                }else if(it.response().code()==204){
                                    market_name.visibility= View.GONE
                                    market_beacon_name.visibility=View.GONE
                                    market_address.visibility = View.GONE
                                    market_phone.visibility = View.GONE
                                    market_time.visibility = View.GONE
                                    market_mac_address.visibility= View.GONE

                                    showToast("해당 정보가 없습니다")
                                }


                            }
                        }
                )
    }
}
