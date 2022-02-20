package com.snystudio.themoviedblist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }
}