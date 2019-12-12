package com.art4muslim.orders.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.art4muslim.orders.R
import com.art4muslim.orders.model.OrderDetails
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_order.view.*

/**
 * Created by µðšţãƒâ ™ on 11/12/2019.
 *  ->
 */
class OrderDetailsAdapter(
    private val mOrderDetails: MutableList<OrderDetails>
) :
    RecyclerView.Adapter<OrderDetailsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderDetailsHolder =
        OrderDetailsHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_order,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = mOrderDetails.size

    override fun onBindViewHolder(holder: OrderDetailsHolder, position: Int) {
        holder.bindView(mOrderDetails[position])
    }
}

class OrderDetailsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val mIvPhoto = itemView.ivPhoto
    private val mTvName = itemView.tvOrderName
    private val mTvQuantity = itemView.tvQuantity
    private val mTvPrice = itemView.tvPrice

    fun bindView(item: OrderDetails) {
        Glide.with(itemView.context)
            .load(item.image)
            .into(mIvPhoto)

        mTvName.text = String.format(itemView.context.getString(R.string.order_name), item.name)
        mTvQuantity.text =
            String.format(itemView.context.getString(R.string.order_quantity), item.quantity)
        mTvPrice.text = String.format(itemView.context.getString(R.string.order_price), item.price)
    }
}