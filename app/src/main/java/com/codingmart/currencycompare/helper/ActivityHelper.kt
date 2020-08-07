package com.codingmart.currencycompare.helper

import android.app.Activity
import android.content.Context
import android.widget.Toast

fun Activity.showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
fun Context.showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()