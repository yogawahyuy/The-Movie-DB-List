package com.snystudio.themoviedblist.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.snystudio.themoviedblist.R
import com.snystudio.themoviedblist.adapter.RecyclerViewMainAdapter
import com.snystudio.themoviedblist.adapter.RecyclerViewUpcomingAdapter
import com.snystudio.themoviedblist.ui.PagingListFilmActivity
import com.snystudio.themoviedblist.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class MainFragment : Fragment() {

    lateinit var recyclerViewMainAdapter: RecyclerViewMainAdapter
    lateinit var recyclerViewUpcomingAdapter: RecyclerViewUpcomingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_main, container, false)
        initRecyclerView(view)
        initViewModel()
        initViewModelUpcoming()
        val lsPopular=view.findViewById<TextView>(R.id.tv_liatselengkapnya_popular)
        lsPopular.setOnClickListener {
            startActivity(Intent(requireContext(),PagingListFilmActivity::class.java))
        }
        return view
    }

        private fun initRecyclerView(view :View){
            val recyclerView= view.findViewById<RecyclerView>(R.id.recycler_view)
            val recyclerViewFreeWatch = view.findViewById<RecyclerView>(R.id.recycler_view_freewatch)
            recyclerView.layoutManager=LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
            recyclerViewMainAdapter= RecyclerViewMainAdapter()
            recyclerView.adapter=recyclerViewMainAdapter
            recyclerViewFreeWatch.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            recyclerViewUpcomingAdapter=RecyclerViewUpcomingAdapter()
            recyclerViewFreeWatch.adapter=recyclerViewUpcomingAdapter

    }

    private fun initViewModel(){
        val viewModel= ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
        viewModel.getLiveDataObsevrer().observe(requireActivity(), Observer {
            if (it!=null){
                recyclerViewMainAdapter.setListData(it)
                recyclerViewMainAdapter.notifyDataSetChanged()
            }else{

            }
        })
        viewModel.loadListOfData()

    }
    private fun initViewModelUpcoming(){
        val viewModelUpcoming=ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
        viewModelUpcoming.getLiveDataUpcomingObserver().observe(requireActivity(), Observer {
            if (it!=null){
                recyclerViewUpcomingAdapter.setListData(it)
                recyclerViewUpcomingAdapter.notifyDataSetChanged()
            }
        })
        viewModelUpcoming.loadListOfUpcomingData()
    }

}