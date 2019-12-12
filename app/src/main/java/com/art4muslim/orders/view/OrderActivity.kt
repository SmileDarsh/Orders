package com.art4muslim.orders.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.art4muslim.orders.R
import com.art4muslim.orders.helper.MapHelper
import com.art4muslim.orders.helper.OnLocationListener
import com.art4muslim.orders.remote.RetrofitWebService
import com.art4muslim.orders.remote.response.OrderResponse
import com.art4muslim.orders.view.dialog.OrderDialogFragment

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable
import java.util.*

/**
 * Created by µðšţãƒâ ™ on 10/12/2019.
 *  ->
 */

class OrderActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private lateinit var mId: String
    private lateinit var mMapHelper: MapHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        mId = intent.getStringExtra("id")!!

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onResume() {
        super.onResume()
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onDirectionEvent(location: String) {
        mMapHelper.onDirectionEvent(location)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.setOnMarkerClickListener(this)
        mMapHelper = MapHelper(this, googleMap, object : OnLocationListener{
            override fun onLocationBack() {
                getDataRetrofit(mId)
            }
        })
    }

    private fun getDataRetrofit(id: String) {
        RetrofitWebService.service.getOrder(id, Locale.getDefault().language)
            .enqueue(object : Callback<OrderResponse> {
                override fun onResponse(
                    call: Call<OrderResponse>,
                    response: Response<OrderResponse>
                ) {
                    val res = response.body()
                    if (res != null) {
                        if(res.status == 200) {
                            mMapHelper.addMarkers(res.data!!)
                        }
                    }
                }

                override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                    t.printStackTrace()
                }

            })
    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        val orderDialogFragment = OrderDialogFragment()
        val bundle = Bundle()
        bundle.putSerializable("order", mMapHelper.getOrder(marker) as Serializable)
        orderDialogFragment.arguments = bundle
        orderDialogFragment.show(supportFragmentManager, "")
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        mMapHelper.onRequestPermissions(requestCode, grantResults)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
