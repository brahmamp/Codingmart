package com.codingmart.currencycompare.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codingmart.currencycompare.database.dao.ExchangeRateDao
import com.codingmart.currencycompare.helper.processRequest
import com.codingmart.currencycompare.models.ExchangeApiRateResponse
import com.codingmart.currencycompare.network.ApiService
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel(
    private val apiService: ApiService,
    private val databaseService: ExchangeRateDao
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val exchangeApiResponse: LiveData<ExchangeApiRateResponse>
        get() = _exchangeApiResponse
    private var _exchangeApiResponse = MutableLiveData<ExchangeApiRateResponse>()

    val errorResponse: LiveData<String>
        get() = _errorResponse

    private var _errorResponse = MutableLiveData<String>()


    fun getExchangeApiRate(base: String, symbols: String) {
        compositeDisposable.add(apiService.getExchangeRateApi().processRequest(
            {
                _exchangeApiResponse.value = it
            },
            { error ->
                error?.let { _errorResponse.value = it }
            }
        ))
    }
}