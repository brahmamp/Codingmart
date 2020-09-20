package com.codingmart.currencycompare.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.codingmart.currencycompare.database.entities.ExchangeRateEntity

@Dao
interface ExchangeRateDao {

    @Query("SELECT * FROM exchange_rate_entity")
    fun getAll(): List<ExchangeRateEntity>

    @Query("SELECT * FROM exchange_rate_entity ORDER BY id DESC LIMIT 5")
    fun getLastFiveConversions(): List<ExchangeRateEntity>

    @Insert
    fun insert(vararg comments: ExchangeRateEntity)
}