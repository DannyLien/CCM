package com.hank.ccm

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hank.ccm.databinding.RowNameViewBinding

class NameAdapter(val names: List<String>) : RecyclerView.Adapter<NameAdapter.NameViewHolder>() {
    private val TAG: String? = NameAdapter::class.java.simpleName

    class NameViewHolder(var view: RowNameViewBinding) : ViewHolder(view.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val binding = RowNameViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return NameViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        val dataList = names.get(position)
        holder.view.tvName.text = dataList
        holder.itemView.setOnClickListener {
            Log.d(
                TAG, "onBindViewHolder: ccm-name-recy-" +
                        " ${position} , ${names.get(position)}"
            )
        }
    }

}







