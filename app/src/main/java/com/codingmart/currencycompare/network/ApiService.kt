package com.codingmart.currencycompare.network

import com.codingmart.currencycompare.models.ExchangeApiRateResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("latest")
    fun getExchangeRateApi(
        @Query("base") base: String,
        @Query("symbols") symbols: String
    ): Observable<ExchangeApiRateResponse>

}