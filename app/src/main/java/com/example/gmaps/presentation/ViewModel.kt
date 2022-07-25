package com.example.gmaps.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gmaps.data.DataDownload
import com.example.gmaps.domain.LineData
import com.example.gmaps.domain.PointData
import kotlinx.coroutines.launch
import java.lang.Exception

class ViewModel: ViewModel() {

    var pointDataList = MutableLiveData<List<PointData>>()
    var lineDataList = MutableLiveData<List<LineData>>()

    fun getPoints() {
        var list: List<PointData>
        viewModelScope.launch {
            try {
                list = DataDownload.getData().Points
                pointDataList.postValue(list)
            }  catch (e: Exception) {
                Log.d("url", e.toString())
            }
        }
    }

    fun getLines() {
        var list: List<LineData>
        viewModelScope.launch {
            try {
                list = DataDownload.getData().Lines
                lineDataList.postValue(list)
            }  catch (e: Exception) {
            }
        }
    }

}