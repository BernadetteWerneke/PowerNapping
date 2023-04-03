package com.example.powernapping.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.powernapping.data.model.AlbumData
import com.example.powernapping.databinding.SongItemLongBinding

class FavoritenAdapter(): RecyclerView.Adapter<FavoritenAdapter.UserHolder>(){

    private var dataset = listOf<AlbumData>()
        class UserHolder(val binding: SongItemLongBinding): RecyclerView.ViewHolder(binding.root){
            fun bind(albumData: AlbumData){
                //binding.songItemLongImage.setImageResource(song.imageResourceSong)
                binding.songItemLongSongnameText.text = albumData.name
                binding.songItemLongInterpreterText.text = albumData.artist_name
                //binding.songItemLongImageStar.setImageResource(song.imageResourceStar)
            }
        }

        fun submitList(newList : List<AlbumData>){
            dataset = newList
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val binding=SongItemLongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserHolder(binding)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        //holder.bind(song.get(position))
        //holder.binding.root.setOnClickListener{
        //    onClick(song.get(position))
        val album = dataset[position]

        holder.binding.songItemLongSongnameText.text = album.name
        holder.binding.songItemLongInterpreterText.text = album.artist_name
        }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
