package com.letsbee.geoproandroid.view.main

import com.letsbee.geoproandroid.model.Responses
import com.letsbee.geoproandroid.retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

object MainRepository {

    private val apiService =  RetrofitClient.apiInterface;
    private val disposable = CompositeDisposable()

    fun getApiCall(onResponse: (List<Responses.GetCountriesResponse>) -> Unit, onError: (String) -> Unit) {
        disposable.add(apiService.getCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onNext ->
                    onResponse(onNext)
                },
                { it.localizedMessage?.let { error -> onError(error) }}
            )
        )
    }

    fun clearDisposable() = disposable.clear()
}