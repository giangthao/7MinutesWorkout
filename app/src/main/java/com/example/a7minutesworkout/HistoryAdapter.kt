package com.example.a7minutesworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minutesworkout.databinding.ItemHistoryRowBinding

class HistoryAdapter(private val historyItem: ArrayList<HistoryEntity>):RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    inner class ViewHolder (binding:ItemHistoryRowBinding):RecyclerView.ViewHolder(binding.root){
        val date = binding.tvItem
        val llHistoryItemMain = binding.llHistoryItemMain
        val positionItem = binding.tvPosition
    }
    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(ItemHistoryRowBinding.inflate(LayoutInflater.from(parent.context)
            ,parent,
            false
            ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val historyDate = historyItem[position]
        holder.date.text = historyDate.date
        holder.positionItem.text = (position+1).toString()

        if (position % 2 == 0) {
            holder.llHistoryItemMain.setBackgroundColor(
                Color.parseColor("#EBEBEB")
            )
        } else {
            holder.llHistoryItemMain.setBackgroundColor(
                Color.parseColor("#FFFFFF")
            )
        }
    }

    override fun getItemCount(): Int {
        return historyItem.size
    }
}