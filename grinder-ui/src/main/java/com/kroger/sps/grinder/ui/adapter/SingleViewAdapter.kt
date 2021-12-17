package com.kroger.sps.grinder.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kroger.sps.grinder.ui.databinding.CustomViewBinding

class SingleViewAdapter(
    private var mDataList: List<String>,
    private var mClickHandler: (String) -> Unit
) : RecyclerView.Adapter<SingleViewAdapter.DataHolder>() {

    inner class DataHolder(private val mBinding: CustomViewBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(s: String) {
            mBinding.apply {
                tvTitle.text = s
                root.setOnClickListener {
                    mClickHandler.invoke(s)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataHolder(CustomViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        holder.bind(mDataList[position])
    }

    override fun getItemCount() = mDataList.size
}