package com.snystudio.themoviedblist.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.snystudio.themoviedblist.R
import com.snystudio.themoviedblist.model.UpcomingMovie
import com.snystudio.themoviedblist.ui.DetailFilmActivity
import kotlinx.android.synthetic.main.recycler_row.view.*

class RecyclerViewUpcomingAdapter: RecyclerView.Adapter<RecyclerViewUpcomingAdapter.MyViewHolders>() {

    private var listDataUpcoming:List<UpcomingMovie>?=null

    fun setListData(listDataUpcoming:List<UpcomingMovie>?){
        this.listDataUpcoming=listDataUpcoming
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewUpcomingAdapter.MyViewHolders {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
        return MyViewHolders(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewUpcomingAdapter.MyViewHolders, position: Int) {
        holder.bind(listDataUpcoming?.get(position)!!)
        holder.itemView.setOnClickListener {
            val intent= Intent(holder.itemView.context, DetailFilmActivity::class.java)
            intent.putExtra("movie_id",listDataUpcoming?.get(position)!!.id)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        if(listDataUpcoming==null)return 0
        return listDataUpcoming?.size!!
    }

    class MyViewHolders(view: View):RecyclerView.ViewHolder(view){
        val poster_image = view.poster_image
        val original_title=view.original_title
        val release_date=view.release_date
        fun bind(data: UpcomingMovie){
            original_title.text=data.originalTitle
            release_date.text=data.releaseDate
            Glide.with(poster_image)
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/"+data.posterPath)
                .into(poster_image)
        }
    }
}