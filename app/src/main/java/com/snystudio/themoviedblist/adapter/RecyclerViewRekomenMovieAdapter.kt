package com.snystudio.themoviedblist.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.snystudio.themoviedblist.R
import com.snystudio.themoviedblist.model.DiscoverMovie
import com.snystudio.themoviedblist.model.RekomendMovie
import com.snystudio.themoviedblist.ui.DetailFilmActivity
import kotlinx.android.synthetic.main.recycler_row.view.*

class RecyclerViewRekomenMovieAdapter:RecyclerView.Adapter<RecyclerViewRekomenMovieAdapter.MyViewHolder>() {


    private var listData:List<RekomendMovie>?=null
    fun setListData(listData:List<RekomendMovie>?){
        this.listData=listData
    }
    class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        val poster_image = view.poster_image
        val original_title=view.original_title
        val release_date=view.release_date
        fun bind(data: RekomendMovie){
            original_title.text=data.originalTitle
            release_date.text=data.releaseDate
            Glide.with(poster_image)
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/"+data.posterPath)
                .into(poster_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.grid_recycler_row,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listData?.get(position)!!)
        holder.itemView.setOnClickListener {
            // Toast.makeText(holder.itemView.context,listData?.get(position)!!.title,Toast.LENGTH_SHORT).show()
            val intent= Intent(holder.itemView.context, DetailFilmActivity::class.java)
            intent.putExtra("movie_id",listData?.get(position)!!.id)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        if(listData==null)return 0
        return listData?.size!!
    }
}