package com.example.gmaps.data

import com.example.gmaps.domain.CoordinatesData
import retrofit2.http.GET

interface DataInterface {
    @GET("getPoint")
    suspend fun getData(): CoordinatesData
}