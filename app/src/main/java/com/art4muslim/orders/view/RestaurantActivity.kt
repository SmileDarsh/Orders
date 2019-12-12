package com.art4muslim.orders.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.art4muslim.orders.R
import com.art4muslim.orders.adapter.RestaurantAdapter
import com.art4muslim.orders.model.Restaurant
import kotlinx.android.synthetic.main.activity_rastaurant.*
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.art4muslim.orders.remote.RetrofitWebService
import com.art4muslim.orders.remote.response.RestaurantResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

/**
 * Created by µðšţãƒâ ™ on 10/12/2019.
 *  ->
 */
class RestaurantActivity : AppCompatActivity() {
    private var isGrid: Boolean = true
    private lateinit var mAdapter: RestaurantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rastaurant)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        getDataRetrofit()

        ibnGrid.setOnClickListener {
            changeListView()
        }
    }

    private fun getDataRetrofit() {
        RetrofitWebService.service.getRestaurants(Locale.getDefault().language)
            .enqueue(object : Callback<RestaurantResponse> {
                override fun onResponse(
                    call: Call<RestaurantResponse>,
                    response: Response<RestaurantResponse>
                ) {
                    val res = response.body()
                    if (res != null) {
                        if (res.status == 200)
                            initRestaurantList(res.data!!)
                        else {
                            pbProgress.visibility = View.GONE
                            Toast.makeText(this@RestaurantActivity, res.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<RestaurantResponse>, t: Throwable) {
                    pbProgress.visibility = View.GONE
                    t.printStackTrace()
                }

            })
    }

    private fun initRestaurantList(list: MutableList<Restaurant>) {
        mAdapter = RestaurantAdapter(list)
        rvRestaurants.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@RestaurantActivity)
            adapter = mAdapter
        }
    }

    private fun changeListView(){
        isGrid = !isGrid
        ibnGrid.setImageResource(if(isGrid) R.drawable.ic_view_grid else R.drawable.ic_view_list)

        rvRestaurants.setLayoutManager(
            if (isGrid) LinearLayoutManager(
                this
            ) else StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        )
        mAdapter.addGrid(isGrid)
        rvRestaurants.adapter = mAdapter
    }
}