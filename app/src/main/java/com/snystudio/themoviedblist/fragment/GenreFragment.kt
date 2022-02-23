package com.snystudio.themoviedblist.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.snystudio.themoviedblist.R
import com.snystudio.themoviedblist.adapter.RecyclerViewGenreMovieAdapter
import com.snystudio.themoviedblist.viewmodel.GenreViewModel
import com.snystudio.themoviedblist.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GenreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class GenreFragment : Fragment() {

    lateinit var recyclerViewGenreMovieAdapter: RecyclerViewGenreMovieAdapter
    lateinit var recyclerView:RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_genre, container, false)
        initRecyclerView(view)
        initViewModel()
        return view
    }

    fun initRecyclerView(view:View){
        recyclerView=view.findViewById(R.id.rv_genre)
        recyclerView.layoutManager= GridLayoutManager(requireContext(),2)
        recyclerViewGenreMovieAdapter= RecyclerViewGenreMovieAdapter()
        recyclerView.adapter=recyclerViewGenreMovieAdapter
    }
    fun initViewModel(){
        val viewModel = ViewModelProvider(requireActivity()).get(GenreViewModel::class.java)
        viewModel.getDataObserver().observe(requireActivity(), Observer {
            if (it!=null) {
                recyclerViewGenreMovieAdapter.setListData(it)
                recyclerViewGenreMovieAdapter.notifyDataSetChanged()
            }
        })
        viewModel.getLoadDataGenre()
    }

}