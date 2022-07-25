package com.example.gmaps.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.gmaps.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.gmaps.databinding.ActivityMapsBinding
import com.google.android.gms.maps.GoogleMap.MAP_TYPE_SATELLITE
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnPolylineClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private val viewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.mapType = MAP_TYPE_SATELLITE

        setupPoints()
        setupLines()
    }

    private fun setupPoints() {
        viewModel.getPoints()

        viewModel.pointDataList.observe(this) {
            for (point in it) {
                val lat = point.geometry.coordinates[0]
                val lng = point.geometry.coordinates[1]
                val alt = point.geometry.coordinates[2]

                val position = LatLng(lat,lng)
                val name = point.properties.name

                mMap.addMarker(MarkerOptions()
                    .position(position)
                    .title(name)
                )

                if (point == it.last()) {
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 50f))
                }
            }
        }
    }

    private fun setupLines() {
        viewModel.getLines()

        viewModel.lineDataList.observe(this) {
            for (line in it) {

                val startLat = line.geometry.coordinates.first()[0]
                val startLng = line.geometry.coordinates.first()[1]

                val endLat = line.geometry.coordinates.last()[0]
                val endLng = line.geometry.coordinates.last()[1]

                val polyline = mMap.addPolyline(
                    PolylineOptions()
                        .clickable(true)
                    .add(
                        LatLng(startLat, startLng),
                        LatLng(endLat, endLng)
                    )
                )

                polyline.tag = "line ${it.indexOf(line)}"
                polyline.color = R.color.teal_200
                mMap.setOnPolylineClickListener(this)
            }
        }
    }

    override fun onPolylineClick(p0: Polyline) {
        Toast.makeText(this, p0.tag.toString(),
            Toast.LENGTH_SHORT).show()
    }

}