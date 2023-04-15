package com.example.powernapping.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.powernapping.R
import com.example.powernapping.data.model.AlbumData
import com.example.powernapping.databinding.SongItemLongBinding

class FavoritenAdapter(): RecyclerView.Adapter<FavoritenAdapter.UserHolder>(){

    private var dataset = listOf<AlbumData>()
        class UserHolder(val binding: SongItemLongBinding): RecyclerView.ViewHolder(binding.root){
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
        val album = dataset[position]

        holder.binding.songItemLongSongnameText.text = album.name
        holder.binding.songItemLongInterpreterText.text = album.artist_name

        //load cover image
        holder.binding.songItemTv.load(album.image) {
            //transformations(CircleCropTransformation())         //circle form
            transformations(RoundedCornersTransformation(30f))
            error(R.drawable.ic_broken_image)                     // if image could not be loaded
            placeholder(R.drawable.woozy_face)         // placeholder face during loading images
            crossfade(enable = true)                              // so that images don't jump
        }
        }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
