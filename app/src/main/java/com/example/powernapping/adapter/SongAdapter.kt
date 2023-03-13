package com.example.powernapping.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.powernapping.R
import com.example.powernapping.data.model.Song

class SongAdapter (
        private val dataset: MutableList<Song>
    ) : RecyclerView.Adapter<SongAdapter.ItemViewHolder>() {

        //der ViewHolder umfasst die View und stellt einen Listeneintrag dar
        inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var songimage: ImageView = itemView.findViewById(R.id.songitem_overview_image)
        }


        /* hier werden neue ViewHolder erstellt */
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

            val itemLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.song_item, parent, false)

            return ItemViewHolder(itemLayout)
        }


        //this function makes a strike through of the text depending on if the checkbox is checked or not,
        //googled this, don't know what an Flag is
        private fun toggleStrikeThroughText(todoTv: TextView, checkBox: Boolean) {
            if (checkBox) {
                todoTv.paintFlags = todoTv.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                todoTv.paintFlags = todoTv.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val todo = dataset[position]
            //holder.songName.text = todo.songTitle
            //holder.interpreter.text = todo.interpret
            holder.songimage.setImageResource(todo.imageResourceSong)
        }

        //damit der LayoutManager weiß, wie lang die RecyclerListe ist
        override fun getItemCount(): Int {
            return dataset.size
        }

    }
