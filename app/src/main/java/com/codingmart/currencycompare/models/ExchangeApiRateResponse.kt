package com.codingmart.currencycompare.models

data class ExchangeApiRateResponse(
    val base: String,
    val date: String,
    val rates: Rates
)

data class Rates(
    val CAD: Double,
    val CNY: Double,
    val EUR: Double,
    val GBP: Double,
    val INR: Double,
    val JPY: Double,
    val RUB: Double,
    val USD: Double
)