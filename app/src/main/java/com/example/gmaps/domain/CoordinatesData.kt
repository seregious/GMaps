package com.example.gmaps.domain

data class CoordinatesData(
    val Points: List<PointData>,
    val Lines: List<LineData>
)

data class PointData (
    val properties: PointProperties,
    val geometry: PointGeometry
        )

data class LineData (
    val geometry: LineGeometry
        )

data class PointProperties (
    val name: String
        )

data class PointGeometry (
    val coordinates: List<Double>
        )

data class LineGeometry (
    val coordinates: List<List<Double>>
        )
