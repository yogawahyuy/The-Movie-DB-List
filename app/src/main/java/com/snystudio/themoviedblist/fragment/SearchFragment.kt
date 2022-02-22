package com.snystudio.themoviedblist.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.snystudio.themoviedblist.R
import com.snystudio.themoviedblist.adapter.RecyclerViewRekomenMovieAdapter
import com.snystudio.themoviedblist.adapter.RecyclerViewSearchMovieAdapter
import com.snystudio.themoviedblist.viewmodel.SearchViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {

    lateinit var recyclerViewRekomenMovieAdapter: RecyclerViewRekomenMovieAdapter
    lateinit var recyclerViewSearchMovieAdapter: RecyclerViewSearchMovieAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerViewSearch: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        val etCariFilm=view.findViewById<EditText>(R.id.edtText_cari_film)
        val btnCariFilm=view.findViewById<Button>(R.id.btn_cari_film)
        val tvTitleHasil=view.findViewById<TextView>(R.id.tv_title_hasil)
        recyclerView=view.findViewById(R.id.rv_search_fragmen)
        recyclerViewSearch=view.findViewById(R.id.rv_hasil_search_fragmen)
        initRecyclerView()
        initViewModel()
        btnCariFilm.setOnClickListener {
            recyclerView.visibility=View.GONE
            recyclerViewSearch.visibility=View.VISIBLE
            tvTitleHasil.text="Hasil Pencarian"
            initRecyclerViewSearch()
            initViewModelSearch(etCariFilm.text.toString())
        }

        return view
    }

    private fun initRecyclerView(){
        recyclerView.layoutManager=GridLayoutManager(requireContext(),2)
        recyclerViewRekomenMovieAdapter= RecyclerViewRekomenMovieAdapter()
        recyclerView.adapter=recyclerViewRekomenMovieAdapter
    }

    private fun initRecyclerViewSearch(){
        recyclerViewSearch.layoutManager=GridLayoutManager(requireContext(),2)
        recyclerViewSearchMovieAdapter= RecyclerViewSearchMovieAdapter()
        recyclerViewSearch.adapter=recyclerViewSearchMovieAdapter
    }
    private fun initViewModel(){
        val viewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)
        viewModel.getDataObserver().observe(requireActivity(), Observer {
            if (it!=null){
                recyclerViewRekomenMovieAdapter.setListData(it)
                recyclerViewRekomenMovieAdapter.notifyDataSetChanged()
            }
        })
        viewModel.loadDataRekomendMovie()
    }

    private fun initViewModelSearch(query:String){
        val viewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)
        viewModel.getDataObserverSearch().observe(requireActivity(), Observer {
            if (it!=null){
                recyclerViewSearchMovieAdapter.setListData(it)
                recyclerViewSearchMovieAdapter.notifyDataSetChanged()
            }
        })
        viewModel.loadDataSearchMovie(query)
    }

}