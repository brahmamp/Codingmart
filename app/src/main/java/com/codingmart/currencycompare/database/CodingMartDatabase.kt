package com.codingmart.currencycompare.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codingmart.currencycompare.database.dao.ExchangeRateDao
import com.codingmart.currencycompare.database.entities.ExchangeRateEntity

@Database(
    entities = [ExchangeRateEntity::class],
    version = 1
)
abstract class CodingMartDatabase : RoomDatabase() {
    abstract fun exchangeRateDao(): ExchangeRateDao
}