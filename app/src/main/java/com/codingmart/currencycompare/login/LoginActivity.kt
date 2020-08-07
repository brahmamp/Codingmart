package com.codingmart.currencycompare.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.codingmart.currencycompare.R
import com.codingmart.currencycompare.helper.showSnackbar
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.login_screen.*
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {

    private val viewModel by inject<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)

        login_button.setOnClickListener {
            startActivityForResult(viewModel.googleSignInIntent, RC_SIGN_IN)
        }

        initLiveDataObserver()
    }

    private fun initLiveDataObserver() {
        viewModel.apply {
            googleLoginError.observe(this@LoginActivity, Observer {
                main_layout.showSnackbar(it, Snackbar.LENGTH_LONG)
            })

            googleLoginSuccess.observe(this@LoginActivity, Observer {
                Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_SHORT).show()
            })
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                viewModel.firebaseAuthWithGoogle(this, account!!)
            } catch (e: ApiException) {
                main_layout.showSnackbar("Google sign in failed: $e.", Snackbar.LENGTH_LONG)
            }
        }
    }

    companion object {
        private const val RC_SIGN_IN = 1001
    }
}