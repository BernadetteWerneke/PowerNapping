package com.example.powernapping.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.powernapping.data.JamendoRepository
import com.example.powernapping.data.remote.GotJamendoApi
import kotlinx.coroutines.launch

class FavoriteViewModel : ViewModel() {
    private val repository = JamendoRepository(GotJamendoApi)

    val alben = repository.alben

    init {
        loadAlbum()
    }

    private fun loadAlbum() {
        viewModelScope.launch {
            repository.loadAlbum()
        }
    }
}