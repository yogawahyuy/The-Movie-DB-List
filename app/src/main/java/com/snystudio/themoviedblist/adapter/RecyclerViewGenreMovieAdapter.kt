package com.snystudio.themoviedblist.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.snystudio.themoviedblist.R
import com.snystudio.themoviedblist.model.DiscoverMovie
import com.snystudio.themoviedblist.model.GenreMovie
import com.snystudio.themoviedblist.ui.DetailFilmActivity
import com.snystudio.themoviedblist.ui.ListFilmActivity
import kotlinx.android.synthetic.main.genre_grid_recycler_row.view.*

class RecyclerViewGenreMovieAdapter : RecyclerView.Adapter<RecyclerViewGenreMovieAdapter.MyViewHolder>() {

    private var listData:List<GenreMovie>?=null

    fun setListData(listData:List<GenreMovie>?){
        this.listData=listData
    }
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvGenre=view.tv_genre
        fun bind(data:GenreMovie){
            tvGenre.text=data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.genre_grid_recycler_row,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listData?.get(position)!!)
        holder.itemView.setOnClickListener {
            val intent= Intent(holder.itemView.context, ListFilmActivity::class.java)
            intent.putExtra("id_genre",listData?.get(position)!!.id)
            intent.putExtra("name_genre",listData?.get(position)!!.name)
            holder.itemView.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        if(listData==null)return 0
        return listData?.size!!
    }


}