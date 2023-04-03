package com.example.powernapping.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.powernapping.data.model.AlbumData
import com.example.powernapping.data.remote.GotJamendoApi

class JamendoRepository (private val api: GotJamendoApi){

    private val _alben = MutableLiveData<List<AlbumData>>()
    val alben: LiveData<List<AlbumData>>
    get() = _alben

    suspend fun loadAlbum(){
        try{
            _alben.value = api.retrofitService.getAlbum().results
        } catch (e: Exception){
            Log.e("JamendoRepository", "Error loading data from API: $e")
        }
    }
}