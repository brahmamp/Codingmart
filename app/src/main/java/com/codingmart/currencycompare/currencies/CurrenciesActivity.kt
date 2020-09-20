package com.codingmart.currencycompare.currencies

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.codingmart.currencycompare.R
import com.codingmart.currencycompare.database.entities.ExchangeRateEntity
import com.codingmart.currencycompare.helper.SharedPreferenceFactory
import com.codingmart.currencycompare.helper.SharedPreferenceProvider
import com.codingmart.currencycompare.home.MainActivity
import com.codingmart.currencycompare.home.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_compare_currency.*
import kotlinx.android.synthetic.main.loading_indicator.*
import org.koin.android.ext.android.inject

class CurrenciesActivity: AppCompatActivity() {

    private val viewModel by inject<MainActivityViewModel>()
    private val sharedPreference: SharedPreferenceProvider = SharedPreferenceFactory.sharedPreferenceProvider
    private var selectedCurrency: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compare_currency)

        val currency = arrayListOf("USD","GBP","INR","RUB","CNY","JPY","EUR","CAD")
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_dropdown_item_1line, // Layout
            currency
        )
        edtCurrencyCode.setAdapter(adapter)
        edtCurrencyCode.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                selectedCurrency = parent.getItemAtPosition(position).toString()
            }

        addBtn.setOnClickListener{
            selectedCurrency?.let {
                loadingIndicator.visibility = View.VISIBLE
                viewModel.getExchangeApiRate(it)
            }?: Toast.makeText(this, "Please select a currency to add", Toast.LENGTH_SHORT).show()



        }

        nextButton.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        initLiveDataObservables()
    }

    private fun initLiveDataObservables() {
        viewModel.apply {
            errorResponse.observe(this@CurrenciesActivity, Observer {
                it
                loadingIndicator.visibility = View.GONE
            })

            exchangeApiMap.observe(this@CurrenciesActivity, Observer {
                loadingIndicator.visibility = View.GONE
                insertRecord(ExchangeRateEntity(0, getBaseCurrency(), selectedCurrency!!, it.getValue(selectedCurrency!!).toString()))
                Toast.makeText(this@CurrenciesActivity, "$selectedCurrency added successfully", Toast.LENGTH_SHORT).show()
            })
        }
    }
}