package com.letsbee.geoproandroid.retrofit

import com.letsbee.geoproandroid.model.Responses
import io.reactivex.Flowable
import retrofit2.http.GET

interface ApiService {

    @GET(Constant.GET_COUNTRIES)
    fun getCountries() : Flowable<List<Responses.GetCountriesResponse>>
}