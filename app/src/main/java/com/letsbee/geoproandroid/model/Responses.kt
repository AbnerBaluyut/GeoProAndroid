package com.letsbee.geoproandroid.model

import com.google.gson.annotations.SerializedName

object Responses {

    data class GetCountriesResponse(
        @SerializedName("name") val countryName: String,
        @SerializedName("capital") val capital: String,
        @SerializedName("alpha2Code") val alphaCode: String,
        @SerializedName("population") val population: String
    )
}