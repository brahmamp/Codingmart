package com.codingmart.currencycompare.google

import android.content.Context
import com.codingmart.currencycompare.R
import com.codingmart.currencycompare.helper.showToast
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

class FirebaseHelper(
    private val context: Context,
    private val googleSignInHelper: GoogleSignInHelper
) {

    private var analytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(context)
    var auth: FirebaseAuth = FirebaseAuth.getInstance()

    val googleSignInIntent = googleSignInHelper.googleSignInIntent

    fun signOut() =
        when {
            auth.currentUser != null -> {
                auth.signOut()
                googleSignInHelper.signOut()
                context.showToast(context.getString(R.string.logged_out_successfully))
            }
            else -> context.showToast(context.getString(R.string.already_logged_out))
        }
}