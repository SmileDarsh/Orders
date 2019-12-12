package com.art4muslim.orders.view.dialog

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.art4muslim.orders.R
import com.art4muslim.orders.model.Order
import com.art4muslim.orders.view.OrderDetailsActivity
import kotlinx.android.synthetic.main.dialog_order.*
import org.greenrobot.eventbus.EventBus
import java.io.Serializable

/**
 * Created by µðšţãƒâ ™ on 11/12/2019.
 *  ->
 */
class OrderDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.dialog_order, container, false)
        if (dialog!!.window != null) {
            dialog!!.window!!.setBackgroundDrawableResource(R.drawable.curved_corners_layout)
            dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        }
        return v
    }

    override fun onResume() {
        super.onResume()
        setWindowLayoutForDialog()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val order = arguments!!.getSerializable("order") as Order

        tvTitle.text = order.userName

        btnOrderDetails.setOnClickListener {
            startActivity(
                Intent(context, OrderDetailsActivity::class.java)
                    .putExtra("orderDetailsList", order.orderDetails as Serializable)
            )
            dismiss()
        }

        btnOrderDirection.setOnClickListener {
            EventBus.getDefault().post(order.location)
            dismiss()
        }

        btnClose.setOnClickListener {
            dismiss()
        }
    }

    private fun setWindowLayoutForDialog() {
        val window = dialog!!.window
        if (window != null) {
            val display = dialog!!.context.resources.displayMetrics

            val displayWidth = display.widthPixels
            val displayHeight = display.heightPixels

            window.setLayout((displayWidth * .70).toInt(), (displayHeight * .50).toInt())
            window.setGravity(Gravity.CENTER)
        }
    }
}