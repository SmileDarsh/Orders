package com.art4muslim.orders.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.art4muslim.orders.R
import com.art4muslim.orders.adapter.OrderDetailsAdapter
import com.art4muslim.orders.model.OrderDetails
import kotlinx.android.synthetic.main.activity_order_details.*

/**
 * Created by µðšţãƒâ ™ on 11/12/2019.
 *  ->
 */
class OrderDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val list = intent.getSerializableExtra("orderDetailsList") as MutableList<OrderDetails>

        initOrderDetailsList(list)
    }

    private fun initOrderDetailsList(list: MutableList<OrderDetails>) {
        val mAdapter = OrderDetailsAdapter(list)
        rvOrderDetails.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@OrderDetailsActivity)
            adapter = mAdapter
        }
    }
}