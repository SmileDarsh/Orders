package com.art4muslim.orders.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.art4muslim.orders.R
import com.art4muslim.orders.remote.RetrofitWebService
import com.art4muslim.orders.remote.request.LoginRequest
import com.art4muslim.orders.remote.response.LoginResponse
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by µðšţãƒâ ™ on 10/12/2019.
 *  ->
 */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setSupportActionBar(toolbar)

        btnLogin.setOnClickListener {
            getLoginRetrofit()
        }
    }

    private fun getLoginRetrofit() {
        showProgress()
        RetrofitWebService.service.login(
            LoginRequest(
                etPhoneNumber.text.toString().trim(),
                etPassword.text.toString().trim(),
                "Gdka52DASWE3DSasWE742Wq",
                "yH52dDDF85sddEWqPNV7D12sW5e"
            )
        ).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val res = response.body()
                if (res != null) {
                    hideProgress()
                    if (res.status == 200)
                        startActivity(Intent(this@LoginActivity, RestaurantActivity::class.java))
                    else
                        Toast.makeText(this@LoginActivity, res.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                hideProgress()
                t.printStackTrace()
            }
        })
    }

    private fun showProgress(){
        pbProgress.visibility = View.VISIBLE
        btnLogin.visibility = View.INVISIBLE
        etPhoneNumber.isEnabled = false
        etPassword.isEnabled = false
    }

    private fun hideProgress(){
        pbProgress.visibility = View.INVISIBLE
        btnLogin.visibility = View.VISIBLE
        etPhoneNumber.isEnabled = true
        etPassword.isEnabled = true

    }
}