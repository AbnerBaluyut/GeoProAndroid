package com.letsbee.geoproandroid.view.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.letsbee.geoproandroid.databinding.CountryItemBinding
import com.letsbee.geoproandroid.model.Responses

class CountryAdapter(private val list: List<Responses.GetCountriesResponse>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder
        = CountryViewHolder(binding = CountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int)
        = holder.bind(country = list[position])

    override fun getItemCount(): Int
        = list.size

    class CountryViewHolder(private val binding: CountryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Responses.GetCountriesResponse) {
            binding.txvCountryName.text = country.countryName
        }
    }
}