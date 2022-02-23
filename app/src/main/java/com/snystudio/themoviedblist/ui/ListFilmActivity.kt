package com.snystudio.themoviedblist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.snystudio.themoviedblist.R
import com.snystudio.themoviedblist.adapter.RecyclerViewListFilmAdapter
import com.snystudio.themoviedblist.viewmodel.ListFilmViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_list_film.*

@AndroidEntryPoint
class ListFilmActivity : AppCompatActivity() {
    lateinit var recyclerViewListFilmAdapter: RecyclerViewListFilmAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_film)
        initRecyclerView()
        initViewModel()
    }

    fun initRecyclerView(){
        rv_list_film.layoutManager= GridLayoutManager(this,2)
        recyclerViewListFilmAdapter= RecyclerViewListFilmAdapter()
        rv_list_film.adapter=recyclerViewListFilmAdapter
    }

    fun initViewModel(){
        val intent=intent
        tv_title_genre.text="Film Dengan Genre "+intent.getStringExtra("name_genre")
        val viewModel=ViewModelProvider(this).get(ListFilmViewModel::class.java)
        viewModel.getLiveDataObsevrer().observe(this, Observer {
            if (it!=null){
                recyclerViewListFilmAdapter.setListData(it)
                recyclerViewListFilmAdapter.notifyDataSetChanged()
            }
        })
        viewModel.loadListOfData(intent.getIntExtra("id_genre",0))

    }
}