package com.letsbee.geoproandroid.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.e
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.letsbee.geoproandroid.databinding.ActivityMainBinding
import com.letsbee.geoproandroid.utils.toast
import com.letsbee.geoproandroid.view.main.adapter.CountryAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var countryAdapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initAdapter()

        binding.swCountry.setOnRefreshListener {
            observeData()
        }

        binding.edtSearch.addTextChangedListener(
            onTextChanged = { searchText, _, _ , _ ->
                val filter = mainActivityViewModel.getCountriesResponse.value?.filter { searchText?.toString() == it.countryName }
//                binding.rcvCountries.adapter = filter?.let { CountryAdapter(list = it) }
            }
        )
    }

    private fun initViewModel() {

        mainActivityViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun initAdapter() {

        binding.rcvCountries.layoutManager = LinearLayoutManager(this)
        observeData()
    }

    private fun observeData() {

        mainActivityViewModel.apply {
            getCountries()
            getCountriesResponse.observe(this@MainActivity, { response ->
                binding.rcvCountries.adapter = CountryAdapter(list = response)
            })
            showErrorMessage.observe(this@MainActivity, { error ->
                toast(message = error, isLengthLong = false)
            })
            isLoading.observe(this@MainActivity, {
                binding.swCountry.isRefreshing = it
            })
        }
    }

    override fun onPause() {
        super.onPause()
        mainActivityViewModel.clearDisposable()
    }
}