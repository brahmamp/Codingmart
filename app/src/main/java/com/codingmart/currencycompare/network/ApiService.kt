package com.codingmart.currencycompare.network

import com.codingmart.currencycompare.models.ExchangeApiRateResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("latest")
    fun getExchangeRateApi(
        @Query("base") base: String = "USD",
        @Query("symbols") symbols: String = "USD,GBP,INR,RUB,CNY,JPY,EUR,CAD"
    ): Observable<ExchangeApiRateResponse>

}