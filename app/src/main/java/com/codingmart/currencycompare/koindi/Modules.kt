package com.codingmart.currencycompare.koindi

import com.codingmart.currencycompare.home.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModule =
    module(override = true) {

        viewModel {
            MainActivityViewModel(service = get())
        }
    }
