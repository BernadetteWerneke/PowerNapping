package com.example.powernapping.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.powernapping.data.JamendoRepository
import com.example.powernapping.data.remote.GotJamendoApi
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }
class FavoriteViewModel : ViewModel() {
    private val repository = JamendoRepository(GotJamendoApi)

    val alben = repository.alben

    //error handling API Status
    private val _loading = MutableLiveData<ApiStatus>()
    val loading: LiveData<ApiStatus>
        get() = _loading

    init {
        loadAlbum()
    }

    private fun loadAlbum() {
        viewModelScope.launch {
            try {
                //try to load
                _loading.value = ApiStatus.LOADING
                repository.loadAlbum()
                //if loading ok than set
                _loading.value = ApiStatus.DONE
            } catch (e: Exception) {                                    //TODO "diesen Part" aus FavoriteViewModel hierhin verschieben-> Wolke erscheint bei Flugmodus
                // if no internet connection
                Log.e("FavoriteViewModel", "error loading albums: $e" )
                _loading.value = ApiStatus.ERROR
            }
        }
    }
}