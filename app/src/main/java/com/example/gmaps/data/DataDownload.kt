package com.example.gmaps.data

import com.example.gmaps.domain.CoordinatesData

object DataDownload {
    suspend fun getData(): CoordinatesData {
        return DataManager.getData.getData()
    }
}