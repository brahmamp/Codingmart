package com.codingmart.currencycompare

import android.app.Application
import com.codingmart.currencycompare.helper.SharedPreferenceFactory
import com.codingmart.currencycompare.helper.SharedPreferenceProvider
import com.codingmart.currencycompare.koindi.myModule
import com.codingmart.currencycompare.koindi.retrofitModule
import com.codingmart.currencycompare.koindi.roomDatabaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CodingMartApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        SharedPreferenceFactory.sharedPreferenceProvider = SharedPreferenceProvider(this)

        startKoin {
            androidContext(this@CodingMartApplication)
            modules(listOf(myModule, retrofitModule, roomDatabaseModule))
        }
    }
}