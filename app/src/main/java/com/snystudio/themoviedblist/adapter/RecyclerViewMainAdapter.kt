package com.snystudio.themoviedblist.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.snystudio.themoviedblist.R
import com.snystudio.themoviedblist.databinding.RecyclerRowBinding
import com.snystudio.themoviedblist.db.home.HomeMovie
import com.snystudio.themoviedblist.model.DiscoverMovie
import com.snystudio.themoviedblist.ui.DetailFilmActivity
import kotlinx.android.synthetic.main.recycler_row.view.*

class RecyclerViewMainAdapter : ListAdapter<HomeMovie,RecyclerViewMainAdapter.MyViewHolder>(HomeMovieComparator()){

//    private var listData:List<DiscoverMovie>?=null
//    fun setListData(listData:List<DiscoverMovie>?){
//        this.listData=listData
//    }
    private var listData:List<HomeMovie>?=null
    fun setListData(listData:List<HomeMovie>?){
        this.listData=listData
    }

    class MyViewHolder(private val binding: RecyclerRowBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(homeMovie: HomeMovie){
            binding.apply {
                originalTitle.text = homeMovie.originalTitle
            releaseDate.text=homeMovie.releaseDate
            Glide.with(itemView)
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/"+homeMovie.posterPath)
                .into(posterImage)
            }
        }
    }

    class HomeMovieComparator : DiffUtil.ItemCallback<HomeMovie>() {
        override fun areItemsTheSame(oldItem: HomeMovie, newItem: HomeMovie) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: HomeMovie, newItem: HomeMovie) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val curentItem=getItem(position)
        if (curentItem!=null){
            holder.bind(curentItem)
        }
    }
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): RecyclerViewMainAdapter.MyViewHolder {
//        val view=LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
//        return MyViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: RecyclerViewMainAdapter.MyViewHolder, position: Int) {
//        holder.bind(listData?.get(position)!!)
//        holder.itemView.setOnClickListener {
//           // Toast.makeText(holder.itemView.context,listData?.get(position)!!.title,Toast.LENGTH_SHORT).show()
//            val intent=Intent(holder.itemView.context,DetailFilmActivity::class.java)
//            intent.putExtra("movie_id",listData?.get(position)!!.id)
//            holder.itemView.context.startActivity(intent)
//        }
//    }
//
//    override fun getItemCount(): Int {
//        if(listData==null)return 0
//        return listData?.size!!
//    }
//
//    class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
//        val poster_image = view.poster_image
//        val original_title=view.original_title
//        val release_date=view.release_date
//        fun bind(data: HomeMovie){
//            original_title.text=data.originalTitle
//            release_date.text=data.releaseDate
//            Glide.with(poster_image)
//                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/"+data.posterPath)
//                .into(poster_image)
//        }
//    }
}