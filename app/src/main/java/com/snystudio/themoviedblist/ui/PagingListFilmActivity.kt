package com.snystudio.themoviedblist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.snystudio.themoviedblist.R
import com.snystudio.themoviedblist.adapter.PagingListFilmAdapter
import com.snystudio.themoviedblist.adapter.RecyclerViewMainAdapter
import com.snystudio.themoviedblist.network.RetrofitServiceInstance
import com.snystudio.themoviedblist.viewmodel.MainActivityViewModel
import com.snystudio.themoviedblist.viewmodel.PagingListFilmViewModel
import com.snystudio.themoviedblist.viewmodel.PagingListFilmViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_list_film.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PagingListFilmActivity : AppCompatActivity() {
    lateinit var recyclerViewMainAdapter: RecyclerViewMainAdapter
    lateinit var pagingListFilmVM:PagingListFilmViewModel
    lateinit var pagingListFilmAdapter:PagingListFilmAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging_list_film)
        initRv()
        setupView()
        //initViewModel()
    }

    private fun initRv(){
       // val pagingAdapter=PagingListFilmAdapter()
//        rv_list_film.layoutManager= GridLayoutManager(this,2)
//        recyclerViewMainAdapter= RecyclerViewMainAdapter()
//        rv_list_film.adapter=recyclerViewMainAdapter
        pagingListFilmAdapter= PagingListFilmAdapter()
        rv_list_film.apply {
            layoutManager=GridLayoutManager(context,2)
            adapter=pagingListFilmAdapter
        }

    }
    private fun setupView(){
        lifecycleScope.launch{
            pagingListFilmVM.popularMovie.collect {
                pagingListFilmAdapter.submitData(it)
            }
        }
    }
//    private fun initVM(){
//        val viewModel=PagingListFilmViewModelFactory()
//    }

    private fun initViewModel(){
        val viewModel= ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getLiveDataObsevrer().observe(this, Observer {
            if (it!=null){
                recyclerViewMainAdapter.setListData(it)
                recyclerViewMainAdapter.notifyDataSetChanged()
            }else{

            }
        })
        viewModel.loadListOfData()

    }
}