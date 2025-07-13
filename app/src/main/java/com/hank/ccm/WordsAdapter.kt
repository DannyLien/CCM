package com.hank.ccm

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hank.ccm.databinding.RowWordViewBinding

class WordsAdapter(val wordsData: List<Word>) :
    RecyclerView.Adapter<WordsAdapter.WordViewHolder>() {
    private val TAG: String? = WordsAdapter::class.java.simpleName

    class WordViewHolder(var view: RowWordViewBinding) : ViewHolder(view.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val binding = RowWordViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return WordViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return wordsData.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val dataList = wordsData.get(position)
        holder.view.tvWordName.text = dataList.name
        holder.view.tvWordDiff.text = dataList.difficulty.toString()
        holder.itemView.setOnClickListener {
            Log.d(
                TAG, "onBindViewHolder: ccm-words-recy-" +
                        "${position} , " +
                        "${wordsData.get(position).name} , " +
                        "${wordsData.get(position).difficulty}"
            )
        }
    }

}
