package com.snystudio.themoviedblist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.snystudio.themoviedblist.adapter.RecyclerViewMainAdapter
import com.snystudio.themoviedblist.adapter.RecyclerViewUpcomingAdapter
import com.snystudio.themoviedblist.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var recyclerViewMainAdapter: RecyclerViewMainAdapter
    lateinit var recyclerViewUpcomingAdapter: RecyclerViewUpcomingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initViewModel()
        initViewModelUpcoming()
    }

    private fun initRecyclerView(){
        recycler_view.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recyclerViewMainAdapter= RecyclerViewMainAdapter()
        recycler_view.adapter=recyclerViewMainAdapter
        recycler_view_freewatch.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recyclerViewUpcomingAdapter=RecyclerViewUpcomingAdapter()
        recycler_view_freewatch.adapter=recyclerViewUpcomingAdapter

    }

    private fun initViewModel(){
        val viewModel=ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getLiveDataObsevrer().observe(this, Observer {
            if (it!=null){
                recyclerViewMainAdapter.setListData(it)
                recyclerViewMainAdapter.notifyDataSetChanged()
            }else{

            }
        })
        viewModel.loadListOfData()

    }
    private fun initViewModelUpcoming(){
        val viewModelUpcoming=ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModelUpcoming.getLiveDataUpcomingObserver().observe(this, Observer {
            if (it!=null){
                recyclerViewUpcomingAdapter.setListData(it)
                recyclerViewUpcomingAdapter.notifyDataSetChanged()
            }
        })
        viewModelUpcoming.loadListOfUpcomingData()
    }
}