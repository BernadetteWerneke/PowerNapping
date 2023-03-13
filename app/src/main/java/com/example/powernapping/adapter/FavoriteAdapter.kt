package com.example.powernapping.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.powernapping.data.model.Song
import com.example.powernapping.databinding.SongItemLongBinding

class FavoriteAdapter(var song: MutableList<Song>): RecyclerView.Adapter<FavoriteAdapter.UserHolder>(){
        class UserHolder(val binding: SongItemLongBinding): RecyclerView.ViewHolder(binding.root){
            fun bind(song: Song){
                binding.songItemLongImage.setImageResource(song.imageResourceSong)
                binding.songItemLongSongnameText.text = song.songTitle
                binding.songItemLongInterpreterText.text = song.interpret
                binding.songItemLongImageStar.setImageResource(song.imageResourceStar)
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val binding=SongItemLongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserHolder(binding)
    }

    override fun getItemCount(): Int {
        return song.size
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(song.get(position))
        //holder.binding.root.setOnClickListener{
        //    onClick(song.get(position))
        }
}
