package com.codingmart.currencycompare.koindi

import androidx.room.Room
import com.codingmart.currencycompare.database.CodingMartDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomDatabaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            CodingMartDatabase::class.java,
            "coding_mart_database"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }


    factory {
        get<CodingMartDatabase>().exchangeRateDao()
    }

}