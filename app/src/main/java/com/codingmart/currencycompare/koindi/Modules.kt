package com.codingmart.currencycompare.koindi

import com.codingmart.currencycompare.google.FirebaseHelper
import com.codingmart.currencycompare.google.GoogleSignInHelper
import com.codingmart.currencycompare.home.MainActivityViewModel
import com.codingmart.currencycompare.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModule =
    module(override = true) {

        single {
            GoogleSignInHelper(context = get())
        }

        single {
            FirebaseHelper(context = get(), googleSignInHelper = get())
        }

        viewModel {
            MainActivityViewModel(apiService = get(), exchangeRateDao = get())
        }

        viewModel {
            LoginViewModel(firebaseHelper = get())
        }
    }
