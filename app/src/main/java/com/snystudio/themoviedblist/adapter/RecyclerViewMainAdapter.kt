package com.snystudio.themoviedblist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.snystudio.themoviedblist.R
import com.snystudio.themoviedblist.model.DiscoverMovie
import kotlinx.android.synthetic.main.recycler_row.view.*

class RecyclerViewMainAdapter:RecyclerView.Adapter<RecyclerViewMainAdapter.MyViewHolder>() {

    private var listData:List<DiscoverMovie>?=null
    fun setListData(listData:List<DiscoverMovie>?){
        this.listData=listData
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewMainAdapter.MyViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewMainAdapter.MyViewHolder, position: Int) {
        holder.bind(listData?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if(listData==null)return 0
        return listData?.size!!
    }

    class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        val poster_image = view.poster_image
        val original_title=view.original_title
        val release_date=view.release_date
        fun bind(data: DiscoverMovie){
            original_title.text=data.originalTitle
            release_date.text=data.releaseDate
            Glide.with(poster_image)
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/"+data.posterPath)
                .into(poster_image)
        }
    }
}