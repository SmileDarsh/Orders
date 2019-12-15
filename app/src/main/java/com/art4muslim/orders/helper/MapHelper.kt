package com.art4muslim.orders.helper

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.art4muslim.orders.model.Order
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*

/**
 * Created by µðšţãƒâ ™ on 11/12/2019.
 *  ->
 */

class MapHelper(
    private val mActivity: Activity,
    private val mMap: GoogleMap,
    private val mCallback: OnLocationListener
) {
    private val REQUEST_PICKLOCATION: Int = View.generateViewId()
    private val mLocation = mutableListOf<Order>()
    private var mMyLocation: Location? = null

    init {
        ActivityCompat.requestPermissions(
            mActivity,
            arrayOf("android.permission.ACCESS_FINE_LOCATION"),
            REQUEST_PICKLOCATION
        )
    }

    fun onDirectionEvent(location: String): String{
        val latLng = location.split(',').map { it.toDouble() }
        return "http://maps.google.com/maps?saddr=" +
                "${mMyLocation!!.latitude},${mMyLocation!!.longitude}&daddr=" +
                "${latLng[0]},${latLng[1]}"
    }

    fun addMarkers(location: MutableList<Order>) {
        if (mLocation.size == 0) {
            mLocation.addAll(location)
            location.forEach {
                val latLng = it.location.split(',').map { location -> location.toDouble() }
                it.marker = mMap.addMarker(
                    MarkerOptions().position(
                        LatLng(
                            latLng[0],
                            latLng[1]
                        )
                    ).title(it.userName)
                )
            }
            showMyLocation()
        }
    }

    fun getOrder(marker: Marker?): Order {
        return mLocation.filter { it.marker == marker }[0]
    }

    fun onRequestPermissions(requestCode: Int, grantResults: IntArray) {
        if (requestCode == REQUEST_PICKLOCATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                if (ContextCompat.checkSelfPermission(
                        mActivity,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    getLocation()
                }
            }
        }
    }

    private fun getLocation() {
        val lm: LocationManager =
            mActivity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(
                mActivity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                mActivity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        mMap.isMyLocationEnabled = true

        mMyLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        if (mMyLocation != null)
            mCallback.onLocationBack()
    }

    private fun showMyLocation() {
        val builder = LatLngBounds.Builder()
        builder.include(LatLng(mMyLocation!!.latitude, mMyLocation!!.longitude))
        mLocation.forEach {
            builder.include(it.marker.position)
        }
        val bounds = builder.build()
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100))
    }
}

interface OnLocationListener {
    fun onLocationBack()
}