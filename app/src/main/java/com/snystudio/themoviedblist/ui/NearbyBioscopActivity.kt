package com.snystudio.themoviedblist.ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.snystudio.themoviedblist.R

class NearbyBioscopActivity : AppCompatActivity(),OnMapReadyCallback {
    private lateinit var mMaps:GoogleMap
    private lateinit var locationClient:FusedLocationProviderClient
    var lat=0.0
    var lang=0.0
    private lateinit var mapsFragment: SupportMapFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nearby_bioscop)
        mapsFragment = supportFragmentManager.findFragmentById(R.id.google_maps) as SupportMapFragment
        mapsFragment.getMapAsync(this)
        locationClient=LocationServices.getFusedLocationProviderClient(this)
        getCurentLocation()
    }

    override fun onMapReady(p0: GoogleMap) {
        mMaps = p0

        val maps = LatLng(lat,lang)
        mMaps.addMarker(
            MarkerOptions()
            .position(maps)
            .title("Marker in Sydney"))
        mMaps.moveCamera(CameraUpdateFactory.newLatLng(maps))
    }

    fun getCurentLocation(){
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val task=locationClient.lastLocation
            task.addOnSuccessListener {
                if (it!=null){
                    lat=it.latitude
                    lang=it.longitude

                }
            }
                return
        }


    }

}