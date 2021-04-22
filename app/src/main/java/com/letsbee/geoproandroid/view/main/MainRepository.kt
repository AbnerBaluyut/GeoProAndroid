package com.letsbee.geoproandroid.view.main

import com.letsbee.geoproandroid.model.Responses
import com.letsbee.geoproandroid.common.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

object MainRepository {

    private val apiService =  RetrofitClient.apiInterface;
    private val disposable = CompositeDisposable()

    fun getApiCall(onResponse: (ArrayList<Responses.GetCountriesResponse>) -> Unit, onError: (String) -> Unit) {
        disposable.add(apiService.getCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    onResponse(it)
                },
                onError = {
                    it.localizedMessage?.let { error -> onError(error) }
                }
            )
        )
    }

    fun clearDisposable() = disposable.clear()
}