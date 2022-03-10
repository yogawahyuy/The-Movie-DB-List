package com.snystudio.themoviedblist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.snystudio.themoviedblist.R
import com.snystudio.themoviedblist.adapter.PagingListFilmAdapter
import com.snystudio.themoviedblist.adapter.RecyclerViewMainAdapter
import com.snystudio.themoviedblist.databinding.ActivityPagingListFilmBinding
import com.snystudio.themoviedblist.model.PagingListUiState
import com.snystudio.themoviedblist.network.RetrofitServiceInstance
import com.snystudio.themoviedblist.utils.ext.collect
import com.snystudio.themoviedblist.utils.ext.collectLast
import com.snystudio.themoviedblist.utils.ext.executeWithAction
import com.snystudio.themoviedblist.viewmodel.MainActivityViewModel
import com.snystudio.themoviedblist.viewmodel.PagingListFilmViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_list_film.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PagingListFilmActivity : AppCompatActivity() {
    private lateinit var binding :ActivityPagingListFilmBinding
    private val viewModel : PagingListFilmViewModel by viewModels()
    @Inject
    lateinit var pagingListFilmAdapter: PagingListFilmAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    setBinding()
    setListener()
    setAdapter()
    collectLast(viewModel.popularMovie,::setFilm)
    }

    private fun setBinding(){
        binding = DataBindingUtil.setContentView(this,R.layout.activity_paging_list_film)
    }
    private fun setListener(){
        binding.btnRetry.setOnClickListener { pagingListFilmAdapter.retry() }
    }

    private fun setAdapter(){
        collect(flow = pagingListFilmAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            action = ::setPagingFilmUiState
        )
        binding.rvListFilm.layoutManager= GridLayoutManager(this,2)
        binding.rvListFilm.adapter=pagingListFilmAdapter
    }
    private fun setPagingFilmUiState(loadState: LoadState) {
        binding.executeWithAction {
            pagingUIFilmState = PagingListUIState(loadState)
        }
    }

    private suspend fun setFilm(filmPaging : PagingData<PagingListUiState>){
        pagingListFilmAdapter.submitData(filmPaging)
    }

}