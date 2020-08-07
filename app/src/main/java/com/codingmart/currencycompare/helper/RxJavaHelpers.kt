package com.codingmart.currencycompare.helper

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

inline fun <T> Observable<T>.processRequest(
    crossinline onNext: (result: T) -> Unit,
    crossinline onError: (message: String?) -> Unit
): Disposable {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { result ->
                onNext(result)
            },
            { err ->
                val message = ProcessThrowable.getMessage(err)
                Timber.e(err)
                onError(message)
            }
        )
}