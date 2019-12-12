package com.art4muslim.orders.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.art4muslim.orders.R
import com.art4muslim.orders.model.Restaurant
import com.art4muslim.orders.view.OrderActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_restaurant.view.*

/**
 * Created by µðšţãƒâ ™ on 10/12/2019.
 *  ->
 */
class RestaurantAdapter(
    private val mRestaurants: MutableList<Restaurant>
) :
    RecyclerView.Adapter<RestaurantViewHolder>() {
    private val LIST_VIEW_ITEM = 0
    private val GRID_VIEW_ITEM = 1
    private var isGrid: Boolean = true

    fun addGrid(grid: Boolean) {
        isGrid = grid
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder =
        RestaurantViewHolder(
            LayoutInflater.from(parent.context).inflate(
                if (viewType == LIST_VIEW_ITEM) R.layout.item_restaurant else R.layout.item_restaurant_grid,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = mRestaurants.size

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bindView(mRestaurants[position])
    }

    override fun getItemViewType(position: Int): Int {
        return if (isGrid) LIST_VIEW_ITEM else GRID_VIEW_ITEM
    }
}

class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val mIvPhoto = itemView.ivPhoto
    private val mTvName = itemView.tvRestaurantName
    private val mTvType = itemView.tvType
    private val mCard = itemView.card

    fun bindView(item: Restaurant) {
        Glide.with(itemView.context)
            .load(item.image)
            .into(mIvPhoto)

        mTvName.text = item.name
        mTvType.text = item.type

        mCard.setOnClickListener {
            it.context.startActivity(
                Intent(it.context, OrderActivity::class.java).putExtra(
                    "id",
                    item.id
                )
            )
        }
    }
}