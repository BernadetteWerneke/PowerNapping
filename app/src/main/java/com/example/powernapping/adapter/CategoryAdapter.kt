package com.example.powernapping.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.powernapping.R
import com.example.powernapping.data.model.Category

class CategoryAdapter(
    private val dataset: List<Category>
) : RecyclerView.Adapter<CategoryAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName = itemView.findViewById<TextView>(R.id.category_name_text)
        val songRecycler = itemView.findViewById<RecyclerView>(R.id.category_song_recycler)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item, parent, false)

        return ItemViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val category = dataset[position]

        holder.categoryName.text = category.categoryName
        holder.songRecycler.adapter = SongAdapter(category.songs)
        }

    override fun getItemCount(): Int {
        return dataset.size
    }

}