package com.codingmart.currencycompare.home

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codingmart.currencycompare.R
import com.codingmart.currencycompare.currencies.CurrenciesActivity
import com.codingmart.currencycompare.helper.NetworkHelper.isConnectedToInternet
import com.codingmart.currencycompare.helper.SharedPreferenceFactory
import com.codingmart.currencycompare.helper.SharedPreferenceProvider
import kotlinx.android.synthetic.main.activity_select_base.*
import org.koin.android.ext.android.inject

class SelectBaseActivity : AppCompatActivity() {

    private val sharedPreference: SharedPreferenceProvider = SharedPreferenceFactory.sharedPreferenceProvider
    private lateinit var selectedBaseCurrency: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_base)

        nextButton.setOnClickListener {
            if(this::selectedBaseCurrency.isInitialized){
                sharedPreference.putPref("base_currency", selectedBaseCurrency)
                startActivity(Intent(this, CurrenciesActivity::class.java))
                finish()
            }else{
                Toast.makeText(this, "Please select base currency", Toast.LENGTH_SHORT).show()
            }
        }

        val currency = arrayListOf("USD","GBP","INR","RUB","CNY","JPY","EUR","CAD")
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_dropdown_item_1line, // Layout
            currency
        )
        edtCurrencyCode.setAdapter(adapter)
        edtCurrencyCode.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                selectedBaseCurrency = parent.getItemAtPosition(position).toString()
            }

    }
}