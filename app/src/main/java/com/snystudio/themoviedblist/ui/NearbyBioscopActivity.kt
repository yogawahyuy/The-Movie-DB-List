package com.snystudio.themoviedblist.ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.snystudio.themoviedblist.R
import com.snystudio.themoviedblist.viewmodel.MapsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NearbyBioscopActivity : AppCompatActivity(),OnMapReadyCallback {
    //private lateinit var mMaps:GoogleMap
    private var mMaps: GoogleMap? = null
    private lateinit var locationClient:FusedLocationProviderClient
    private val LOCATION_PERMISSION_CODE=1000
    private var lat:Double=0.0
    private var lang:Double=0.0
   // private lateinit var mapsFragment: SupportMapFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nearby_bioscop)
        locationClient=LocationServices.getFusedLocationProviderClient(this)
        getCurentLocation()
    }

    override fun onMapReady(p0: GoogleMap) {
//        mMaps = p0
//        val maps = LatLng(lat,lang)
//        Log.d("Nearby bioscop", "onMapReady: "+maps)
//        mMaps.addMarker(
//            MarkerOptions()
//            .position(maps)
//            .title("Marker in Sydney"))
//        mMaps.moveCamera(CameraUpdateFactory.newLatLng(maps))
        mMaps = p0
        val maps = LatLng(lat,lang)
        mMaps!!.animateCamera(CameraUpdateFactory.newLatLng(maps))
        mMaps!!.addMarker(MarkerOptions().position(maps))
    }

    fun getCurentLocation(){
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // request permission
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_CODE);
            return
        }
        locationClient.lastLocation.addOnSuccessListener {
            lat=it.latitude
            lang=it.longitude
            Log.d("nearby bisocop", "getCurentLocation: "+it.latitude+" "+it.longitude)
            Log.d("nearby bisocop", "getCurentLocation: "+lat+" "+lang)
            val mapsFragment = supportFragmentManager.findFragmentById(R.id.google_maps) as SupportMapFragment
            mapsFragment.getMapAsync(this)
            initViewModel()
        }.addOnFailureListener {
            Toast.makeText(this, "failed get location", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            LOCATION_PERMISSION_CODE->{
                if (grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){

                }else{
                    Toast.makeText(this, "Anda Harus mengijinkan akses lokasi", Toast.LENGTH_SHORT).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    fun initViewModel(){
        val maps=LatLng(lat,lang)
        mMaps?.clear()
        val viewModel=ViewModelProvider(this).get(MapsViewModel::class.java)
        viewModel.getDataObserver().observe(this, Observer {
            if (it!=null){
                for (i in 0 until it.size) {
                    val markerOptions = MarkerOptions()
                    val lat= it.get(i).geometry?.location?.lat!!
                    val long = it.get(i).geometry?.location?.lng!!
                    val placeName=it.get(i).name
                    val latLang=LatLng(lat,long)

                    markerOptions.position(latLang)
                    markerOptions.title(placeName)
                    markerOptions.snippet(i.toString())
                    mMaps?.addMarker(markerOptions)
                    mMaps?.moveCamera(CameraUpdateFactory.newLatLng(latLang))
                    mMaps?.animateCamera(CameraUpdateFactory.zoomTo(15f))
                    Log.d("nearbyplace", "initViewModel: "+latLang+" "+placeName)
                }


            }
        })
        Log.d("nearbyBisocop", "initViewModel: "+maps.toString())
        val latLong=""+lat+","+lang
        viewModel.loadDataMaps(latLong,1500,"movie_theater")
    }

}