package com.codingmart.currencycompare.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exchange_rate_entity")
class ExchangeRateEntity (
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "baseCurrency")
    val baseCurrency: String,

    @ColumnInfo(name = "currency2Compare")
    val currency2Compare: String,

    @ColumnInfo(name = "conversionValue")
    val conversionValue: String
)