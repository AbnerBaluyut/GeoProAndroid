package com.letsbee.geoproandroid.view.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmadrosid.svgloader.SvgLoader
import com.letsbee.geoproandroid.R
import com.letsbee.geoproandroid.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        SvgLoader.pluck()
            .with(this)
            .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
            .load(intent.getStringExtra("flag"), binding.imgFlag)

        binding.txvCountryName.text = intent.getStringExtra("countryName")
        binding.txvCapital.text = intent.getStringExtra("capital")
        binding.txvAlphaCode.text = intent.getStringExtra("alphaCode")
        binding.txvPopulation.text = intent.getStringExtra("population")
    }
}