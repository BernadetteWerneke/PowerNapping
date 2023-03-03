package com.example.powernapping.data.model

data class Song(
    val songTitle: String,
    val interpret: String,
    val imageResource: Int,
    val favorite: Boolean = false
)
