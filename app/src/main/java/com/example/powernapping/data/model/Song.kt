package com.example.powernapping.data.model

data class Song(
    val songTitle: String,
    val interpret: String,
    val imageResourceSong: Int,
    val imageResourceStar: Int,
    val favorite: Boolean = false
)
