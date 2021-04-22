package com.letsbee.geoproandroid.view.main
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.letsbee.geoproandroid.model.Responses

class MainViewModel : ViewModel() {

    val getCountriesResponse = MutableLiveData<ArrayList<Responses.GetCountriesResponse>>()

    val showErrorMessage = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    fun getCountries() {
        isLoading.value = true
        MainRepository.getApiCall(
            onResponse = {
                isLoading.value = false
                getCountriesResponse.value = it
            },
            onError = {
                isLoading.value = false
                showErrorMessage.value = it
            }
        )
    }

    fun clearDisposable() {
        isLoading.value = false
        MainRepository.clearDisposable()
    }
}