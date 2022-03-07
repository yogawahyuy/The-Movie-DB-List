package com.snystudio.themoviedblist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.snystudio.themoviedblist.R
import com.snystudio.themoviedblist.model.DiscoverMovie
import kotlinx.android.synthetic.main.grid_recycler_row.view.*

class PagingListFilmAdapter :PagingDataAdapter<DiscoverMovie,PagingListFilmAdapter.ViewHolder>(DataDifferentiator) {


    class ViewHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.original_title.text=getItem(position)?.originalTitle
        Glide.with(holder.itemView.poster_image)
            .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/"+getItem(position)?.posterPath)
            .into(holder.itemView.poster_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.grid_recycler_row,parent,false))
    }

    object DataDifferentiator: DiffUtil.ItemCallback<DiscoverMovie>(){
        override fun areItemsTheSame(oldItem: DiscoverMovie, newItem: DiscoverMovie): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: DiscoverMovie, newItem: DiscoverMovie): Boolean {
            return oldItem==newItem
        }

    }
}