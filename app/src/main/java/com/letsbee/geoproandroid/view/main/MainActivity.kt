package com.letsbee.geoproandroid.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.letsbee.geoproandroid.common.utils.isEmpty
import com.letsbee.geoproandroid.common.utils.isNotEmpty
import com.letsbee.geoproandroid.common.utils.openActivity
import com.letsbee.geoproandroid.databinding.ActivityMainBinding
import com.letsbee.geoproandroid.common.utils.toast
import com.letsbee.geoproandroid.view.detail.DetailActivity
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
            mainActivityViewModel.refresh()
        }

        binding.edtSearch.addTextChangedListener {
            countryAdapter.searchFilter(it.toString())
        }
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
                countryAdapter = CountryAdapter(activity = this@MainActivity, list = response) {
                    openActivity(DetailActivity::class.java) {
                        putString("flag", it.flag)
                        putString("countryName", it.countryName)
                        putString("capital", it.capital)
                        putString("alphaCode", it.alphaCode)
                        putString("population", it.population)
                    }
                }
                binding.rcvCountries.adapter = countryAdapter

                if (binding.edtSearch.isNotEmpty()) {
                    countryAdapter.searchFilter((binding.edtSearch.text.toString()))
                }
            })

            showErrorMessage.observe(this@MainActivity, { error ->
                toast(message = error, isLengthLong = false)
            })

            isLoading.observe(this@MainActivity, {
                binding.edtSearch.isEnabled = !it
                binding.swCountry.apply {
                    isRefreshing = it
                    isEnabled = !it
                }
            })
        }
    }

    override fun onPause() {
        super.onPause()
        mainActivityViewModel.clearDisposable()
    }
}