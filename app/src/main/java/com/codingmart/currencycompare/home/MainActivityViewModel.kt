package com.codingmart.currencycompare.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codingmart.currencycompare.database.CodingMartDatabase
import com.codingmart.currencycompare.database.dao.ExchangeRateDao
import com.codingmart.currencycompare.database.entities.ExchangeRateEntity
import com.codingmart.currencycompare.helper.SharedPreferenceFactory
import com.codingmart.currencycompare.helper.SharedPreferenceProvider
import com.codingmart.currencycompare.helper.processRequest
import com.codingmart.currencycompare.network.ApiService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.disposables.CompositeDisposable


class MainActivityViewModel(
    private val apiService: ApiService,
    private val exchangeRateDao: ExchangeRateDao,
    private val sharedPreference: SharedPreferenceProvider = SharedPreferenceFactory.sharedPreferenceProvider
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val exchangeApiMap: LiveData<HashMap<String, Double>>
        get() = _exchangeApiMap
    private var _exchangeApiMap = MutableLiveData<HashMap<String, Double>>()

    val lastFiveConversions: LiveData<List<ExchangeRateEntity>>
        get() = _lastFiveConversions
    private var _lastFiveConversions = MutableLiveData<List<ExchangeRateEntity>>()

    val errorResponse: LiveData<String>
        get() = _errorResponse

    private var _errorResponse = MutableLiveData<String>()


    fun getExchangeApiRate(currencyToCompare: String = "USD") {
        val baseCurrency = sharedPreference.getPref("base_currency", "")
        compositeDisposable.add(apiService.getExchangeRateApi(baseCurrency, currencyToCompare).processRequest(
            {
                val jsonTut: String = Gson().toJson(it.rates)
                _exchangeApiMap.value = Gson().fromJson(jsonTut,
                    object : TypeToken<HashMap<String, Double>>() {}.getType())
            },
            { error ->
                error?.let { _errorResponse.value = it }
            }
        ))
    }

    fun getBaseCurrency() = sharedPreference.getPref("base_currency", "")

    fun insertRecord(exchangeRateEntity: ExchangeRateEntity){
        exchangeRateDao.insert(exchangeRateEntity)
    }

    fun getLastFiveConversions(){
        _lastFiveConversions.value = exchangeRateDao.getLastFiveConversions()
    }
}