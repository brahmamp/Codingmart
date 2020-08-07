package com.codingmart.currencycompare.login

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codingmart.currencycompare.google.FirebaseHelper
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.GoogleAuthProvider

class LoginViewModel(private val firebaseHelper: FirebaseHelper) : ViewModel() {

    val googleLoginError: LiveData<String>
        get() = _googleLoginError

    private var _googleLoginError = MutableLiveData<String>()

    val googleLoginSuccess: LiveData<String>
        get() = _googleLoginSuccess

    private var _googleLoginSuccess = MutableLiveData<String>()

    val googleSignInIntent = firebaseHelper.googleSignInIntent

    fun signOut() = firebaseHelper.signOut()

    fun isUserAlreadyLoggedIn(): Boolean = firebaseHelper.auth.currentUser != null

    fun firebaseAuthWithGoogle(context: Activity, account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseHelper.auth.signInWithCredential(credential)
            .addOnCompleteListener(context) { task ->
                when {
                    task.isSuccessful -> {
                        _googleLoginSuccess.value = SUCCESS
                    }
                    else -> _googleLoginError.value = "${task.exception?.message}"
                }
            }
    }

    companion object {
        const val SUCCESS = "success"
    }
}