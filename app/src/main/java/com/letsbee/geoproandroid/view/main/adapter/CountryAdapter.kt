package com.letsbee.geoproandroid.view.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.letsbee.geoproandroid.R
import com.letsbee.geoproandroid.model.Responses

class CountryAdapter(private val list: ArrayList<Responses.GetCountriesResponse>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent,false)
        return CountryViewHolder(binding = itemView)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int)
        = holder.bind(list[position])

    override fun getItemCount(): Int
        = list.size

    class CountryViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        fun bind(country: Responses.GetCountriesResponse) {
//            binding.
        }
    }
}