package com.codingmart.currencycompare.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codingmart.currencycompare.database.dao.ExchangeRateDao
import com.codingmart.currencycompare.helper.processRequest
import com.codingmart.currencycompare.models.ExchangeApiRateResponse
import com.codingmart.currencycompare.network.ApiService
import io.reactivex.disposables.CompositeDisposable

class SelectBaseViewModel(
    private val apiService: ApiService,
    private val databaseService: ExchangeRateDao
) : ViewModel() {

}