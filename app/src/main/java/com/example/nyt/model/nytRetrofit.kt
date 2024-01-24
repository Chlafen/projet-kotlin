package com.example.nyt.model;

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object nytRetrofit {
    private const val baseUrl = "https://api.nytimes.com/svc/"

    private val retrofit = Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: nytAPI by lazy { retrofit.create(nytAPI::class.java) }
}
