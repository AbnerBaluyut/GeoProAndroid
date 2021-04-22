package com.letsbee.geoproandroid.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.e
import androidx.lifecycle.ViewModelProvider
import com.letsbee.geoproandroid.databinding.ActivityMainBinding
import com.letsbee.geoproandroid.utils.toast

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        observers()
    }

    private fun initViewModel() {

        mainActivityViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun observers() {

        mainActivityViewModel.apply {
            getCountries()
            getCountriesResponse.observe(this@MainActivity, { response ->
                response.forEach {
                    e("Countries:", it.countryName)
                }
            })
            showErrorMessage.observe(this@MainActivity, { error ->
                toast(message = error, isLengthLong = false)
            })
            isLoading.observe(this@MainActivity, {
                toast(message = "isLoading: $it", isLengthLong = false)
            })
        }
    }

    override fun onPause() {
        super.onPause()
        mainActivityViewModel.clearDisposable()
    }
}