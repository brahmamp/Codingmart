package com.deltanxt.travelsocial.dealdetails

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codingmart.currencycompare.R
import com.codingmart.currencycompare.database.entities.ExchangeRateEntity
import com.codingmart.currencycompare.home.CurrenciesViewHolder

class CurrenciesAdapter : RecyclerView.Adapter<CurrenciesViewHolder>() {

    var currencies: List<ExchangeRateEntity> = listOf()
//    var values: List<Double> = listOf()

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesViewHolder {
        context = parent.context
        val v =
            LayoutInflater.from(context).inflate(R.layout.currencies_list_item, parent, false)
        return CurrenciesViewHolder(v)
    }

    override fun getItemCount(): Int {
        return currencies.size
    }

    override fun onBindViewHolder(holder: CurrenciesViewHolder, position: Int) {
        holder.bindView(context, currencies[position])
    }
}