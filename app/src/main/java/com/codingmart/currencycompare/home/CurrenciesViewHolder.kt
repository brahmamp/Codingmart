package com.codingmart.currencycompare.home

import android.content.Context
import android.icu.text.AlphabeticIndex
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codingmart.currencycompare.R
import com.codingmart.currencycompare.database.entities.ExchangeRateEntity

class CurrenciesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(context: Context, currencyConversion: ExchangeRateEntity) {
        val currencyValue = itemView.findViewById<TextView>(R.id.currency_value)

        currencyValue.text = "${currencyConversion.baseCurrency} to ${currencyConversion.currency2Compare} = ${currencyConversion.conversionValue}"

    }
}