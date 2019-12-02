package com.example.gamebacklog.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Gamebacklog
import kotlinx.android.synthetic.main.gamebacklog_item.view.*

class GamebacklogAdapter(private val gamebacklog: List<Gamebacklog>) :
    RecyclerView.Adapter<GamebacklogAdapter.ViewHolder>() {

    /**
     * Creates and returns a ViewHolder object, inflating into our custom item layout
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.gamebacklog_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return gamebacklog.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(gamebacklog[position])
    }

    /**
     * Binds data in recyclerviewer
     */
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(gamebacklog: Gamebacklog) {
            itemView.tvTitle.text = gamebacklog.title
            itemView.tvPlatform.text = gamebacklog.platform
            itemView.tvDay.text = gamebacklog.day
            itemView.tvMonth.text = gamebacklog.month
            itemView.tvYear.text = gamebacklog.year
        }

    }
}