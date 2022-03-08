package com.snystudio.themoviedblist.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView

class FooterAdapter(private val retry: () -> Unit): LoadStateAdapter<FooterAdapter.FooterViewHolder>() {
    override fun onBindViewHolder(holder: FooterViewHolder, loadState: LoadState) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): FooterViewHolder {
        TODO("Not yet implemented")
    }
    class FooterViewHolder(
//        private val binding: ItemPagingFooterBinding,
//        retry: () -> Unit
    view: View
    ) : RecyclerView.ViewHolder(view) {

//        init {
//            binding.btnRetry.setOnClickListener { retry.invoke() }
//        }
//
//        fun bind(loadState: LoadState) {
//            binding.executeWithAction {
//                footerUiState = FooterUiState(loadState)
//            }
//        }
    }
}