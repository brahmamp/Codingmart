package com.codingmart.currencycompare.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingmart.currencycompare.R
import com.codingmart.currencycompare.currencies.CurrenciesActivity
import com.codingmart.currencycompare.helper.NetworkHelper.isConnectedToInternet
import com.deltanxt.travelsocial.dealdetails.CurrenciesAdapter
import kotlinx.android.synthetic.main.login_screen.*
import kotlinx.android.synthetic.main.main_activity.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel by inject<MainActivityViewModel>()
    private val currencyAdapter: CurrenciesAdapter by lazy { CurrenciesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val baseCurrency = viewModel.getBaseCurrency()
        base_currency_text.text = String.format(getString(R.string.base_currency_value), baseCurrency, viewModel.getBaseCurrency())
        viewModel.getLastFiveConversions()

        currencies_list.layoutManager = LinearLayoutManager(this)

        currencies_list.adapter = currencyAdapter

        refresh_button.setOnClickListener{
            viewModel.getExchangeApiRate()
        }

        edit_base_currency.setOnClickListener{
            startActivity(Intent(this, SelectBaseActivity::class.java))
            finish()
        }

        add_more_currency_button.setOnClickListener {
            startActivity(Intent(this, CurrenciesActivity::class.java))
            finish()
        }

        initLiveDataObservables()
    }

    private fun initLiveDataObservables() {
        viewModel.apply {

            lastFiveConversions.observe(this@MainActivity, Observer {
                currencyAdapter.currencies = it
                currencyAdapter.notifyDataSetChanged()
            })
        }
    }
}