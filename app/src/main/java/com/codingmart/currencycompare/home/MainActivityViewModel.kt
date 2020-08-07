package com.codingmart.currencycompare.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codingmart.currencycompare.helper.processRequest
import com.codingmart.currencycompare.network.ApiService
import com.codingmart.currencycompare.models.ExchangeApiRateResponse
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel(private val service: ApiService) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val exchangeApiResponse: LiveData<ExchangeApiRateResponse>
        get() = _exchangeApiResponse
    private var _exchangeApiResponse = MutableLiveData<ExchangeApiRateResponse>()

    val errorResponse: LiveData<String>
        get() = _errorResponse

    private var _errorResponse = MutableLiveData<String>()


    fun getExchangeApiRate(base: String, symbols: String) {
        compositeDisposable.add(service.getExchangeRateApi().processRequest(
            {
                _exchangeApiResponse.value = it
            },
            { error ->
                error?.let { _errorResponse.value = it }
            }
        ))
    }
}