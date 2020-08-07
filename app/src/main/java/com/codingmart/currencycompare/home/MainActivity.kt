package com.codingmart.currencycompare.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.codingmart.currencycompare.R
import com.codingmart.currencycompare.helper.NetworkHelper.isConnectedToInternet
import kotlinx.android.synthetic.main.login_screen.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel by inject<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)

        login_button.setOnClickListener {
            if (isConnectedToInternet(this)) {
                viewModel.getExchangeApiRate("", "")
            }
        }

        initLiveDataObservables()
    }

    private fun initLiveDataObservables() {
        viewModel.apply {
            errorResponse.observe(this@MainActivity, Observer {
                it
            })

            exchangeApiResponse.observe(this@MainActivity, Observer {
                it
            })
        }
    }
}