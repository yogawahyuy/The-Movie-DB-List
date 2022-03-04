package com.snystudio.themoviedblist

import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.snystudio.themoviedblist.adapter.RecyclerViewMainAdapter
import com.snystudio.themoviedblist.adapter.RecyclerViewUpcomingAdapter
import com.snystudio.themoviedblist.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.myfragmentNav)
        bottom_navigation.setupWithNavController(navController)
        handleNotificationData()
    }

    fun handleNotificationData(){
        val data=intent.extras
        if (data!=null){
            if (data.containsKey("title")||data.containsKey("message")||data.containsKey("deepLink")){
                Log.d("main", "handleNotificationData: "+data.getString("deepLink"))
                val intent = Intent(this,MainActivity::class.java)
                intent.action= Intent.ACTION_VIEW
                intent.data= Uri.parse(data.getString("deepLink"))
                intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TOP

                startActivity(intent)
            }
        }
    }

}