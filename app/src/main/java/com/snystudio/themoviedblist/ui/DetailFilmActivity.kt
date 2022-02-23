package com.snystudio.themoviedblist.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.snystudio.themoviedblist.R
import com.snystudio.themoviedblist.viewmodel.DetailMovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail_film.*
@AndroidEntryPoint
class DetailFilmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_film)
        initViewModel()
        btn_bioskop.setOnClickListener {
            val intent=Intent(this,NearbyBioscopActivity::class.java)
            startActivity(intent)
        }
    }

    fun initViewModel(){
        val intent=intent
        val viewModel=ViewModelProvider(this).get(DetailMovieViewModel::class.java)
        viewModel.getDataObsever().observe(this, Observer {
            if (it!=null){
                Glide.with(this).load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/"+it.backdropPath).into(ivBackdrop)
                Glide.with(this).load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/"+it.posterPath).into(ivPoster)
                tvVoteAverage.text=it.voteAverage.toString()
                tvMovieTitleValue.text=it.title
                tvGnreValue.text=it.genres.get(0).name
                tvDuration.text=it.releaseDate
                tvDescriptionValue.text=it.overview
            }
        })
        viewModel.loadOfData(intent.getIntExtra("movie_id",0))

        viewModel.getDataObserverVideos().observe(this, Observer {
            if (it!=null){
               for (i in it.indices){
                   if (it.get(i).official==true&&it.get(i).type=="Trailer"){
                       val key=it.get(i).key
                       btn_trailer.setOnClickListener {
                           val intent=Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://"+key))
                           try {
                               startActivity(intent)
                           }catch (e: ActivityNotFoundException){
                               Toast.makeText(this, "Instal Apllikasi Youtube", Toast.LENGTH_SHORT).show()
                           }
                       }
                   }
               }
            }
        })
        viewModel.loadOfDataVideos(intent.getIntExtra("movie_id",0))
    }
}