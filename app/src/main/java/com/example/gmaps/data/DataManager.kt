package com.example.gmaps.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataManager {
    private var retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var getData: DataInterface = retrofit.create(DataInterface::class.java)
}