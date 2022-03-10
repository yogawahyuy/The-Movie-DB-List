package com.snystudio.themoviedblist.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.snystudio.themoviedblist.R
import com.snystudio.themoviedblist.databinding.GridRecyclerRowBinding
import com.snystudio.themoviedblist.model.DiscoverMovie
import com.snystudio.themoviedblist.model.PagingListUiState
import com.snystudio.themoviedblist.utils.ext.executeWithAction
import kotlinx.android.synthetic.main.grid_recycler_row.view.*
import javax.inject.Inject

class PagingListFilmAdapter @Inject constructor() :PagingDataAdapter<PagingListUiState,PagingListFilmAdapter.ViewHolder>(DataDifferentiator) {


    class ViewHolder(private val binding: GridRecyclerRowBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(pagingListUiState: PagingListUiState){
            binding.executeWithAction{
                this.pagingUiState=pagingListUiState
            }
        }
    }

    object DataDifferentiator: DiffUtil.ItemCallback<PagingListUiState>(){
        override fun areItemsTheSame(
            oldItem: PagingListUiState,
            newItem: PagingListUiState
        ): Boolean {
            return oldItem.getOriginalTitle()==oldItem.getOriginalTitle()
        }

        override fun areContentsTheSame(
            oldItem: PagingListUiState,
            newItem: PagingListUiState
        ): Boolean {
            return oldItem==newItem
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { pagingListUiState -> holder.bind(pagingListUiState)   }
        Log.d("pagingAdapter", "onBindViewHolder: "+getItem(position)?.getPosterPath())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = inflate<GridRecyclerRowBinding>(LayoutInflater.from(parent.context),R.layout.grid_recycler_row,parent,false)
        return ViewHolder(binding)
    }




}