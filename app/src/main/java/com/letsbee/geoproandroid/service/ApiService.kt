package com.letsbee.geoproandroid.service

import com.letsbee.geoproandroid.common.Constant
import com.letsbee.geoproandroid.model.Responses
import io.reactivex.Flowable
import retrofit2.http.GET

interface ApiService {

    @GET(Constant.GET_COUNTRIES)
    fun getCountries() : Flowable<ArrayList<Responses.GetCountriesResponse>>
}