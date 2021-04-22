package com.letsbee.geoproandroid.view.main.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmadrosid.svgloader.SvgLoader
import com.letsbee.geoproandroid.R
import com.letsbee.geoproandroid.databinding.CountryItemBinding
import com.letsbee.geoproandroid.model.Responses
import java.util.*
import kotlin.collections.ArrayList

class CountryAdapter(
    private val activity: Activity,
    private val list: ArrayList<Responses.GetCountriesResponse>,
    private val selectedItem: (Responses.GetCountriesResponse) -> Unit
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    private var filterList: ArrayList<Responses.GetCountriesResponse> = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder
        = CountryViewHolder(binding = CountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int)
        = holder.bind(country = filterList[position])

    override fun getItemCount(): Int
        = filterList.size

    inner class CountryViewHolder(private val binding: CountryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Responses.GetCountriesResponse) {
            binding.apply {
                lnlAdapter.setOnClickListener { selectedItem(country) }
                txvCountryName. text = country.countryName
                txvCioc.text = country.cioc
                SvgLoader.pluck()
                    .with(activity)
                    .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                    .load(country.flag, imgFlag)
            }
        }
    }

    fun searchFilter(constraint: String) {
        filterList = if (constraint.isEmpty()) {
            list
        } else {
            val resultList = ArrayList<Responses.GetCountriesResponse>()
            list.forEach { row ->
                if (row.alphaCode.toLowerCase(Locale.ROOT).contains(constraint.toLowerCase(Locale.ROOT)) || row.countryName.toLowerCase(Locale.ROOT).contains(constraint.toLowerCase(Locale.ROOT))) {
                    resultList.add(row)
                }
            }
            resultList
        }
        notifyDataSetChanged()
    }
}