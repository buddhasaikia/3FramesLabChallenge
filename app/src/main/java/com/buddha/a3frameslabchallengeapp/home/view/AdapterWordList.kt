package com.buddha.a3frameslabchallengeapp.home.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.buddha.a3frameslabchallengeapp.R
import com.buddha.a3frameslabchallengeapp.data.Word
import java.util.*


class AdapterWordList(list: List<Word>?) : RecyclerView.Adapter<AdapterWordList.MyViewHolder>() {
    private val list: List<Word>

    init {
        this.list = ArrayList(list)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item_word, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val word = list[position]
        holder.lblWord.text = word.word
        holder.lblCounter.text = word.count.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lblWord: TextView = itemView.findViewById(R.id.lblWord)
        val lblCounter: TextView = itemView.findViewById(R.id.lblCounter)

    }
}
