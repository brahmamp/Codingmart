package com.codingmart.currencycompare.home

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codingmart.currencycompare.R
import com.codingmart.currencycompare.helper.NetworkHelper.isConnectedToInternet
import kotlinx.android.synthetic.main.activity_select_base.*
import org.koin.android.ext.android.inject

class SelectBaseActivity : AppCompatActivity() {

    private val viewModel by inject<SelectBaseViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_base)

        nextButton.setOnClickListener {
            if (isConnectedToInternet(this)) {
            }
        }

        val currency = arrayListOf("USD,GBP,INR,RUB,CNY,JPY,EUR,CAD")
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_dropdown_item_1line, // Layout
            currency
        )
        edtCurrencyCode.setAdapter(adapter)
        edtCurrencyCode.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedItem = parent.getItemAtPosition(position).toString()
                // Display the clicked item using toast
                Toast.makeText(applicationContext, "Selected : $selectedItem", Toast.LENGTH_SHORT)
                    .show()
            }


        initLiveDataObservables()
    }

    private fun initLiveDataObservables() {
        viewModel.apply {

        }
    }
}