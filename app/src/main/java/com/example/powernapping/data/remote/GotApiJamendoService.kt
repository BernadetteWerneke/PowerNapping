package com.example.powernapping.data.remote

import com.example.powernapping.data.model.GotSong
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

//GET-URL-Form: http[s]://api.jamendo.com/<Version>/<Entität>/<Subentität>/?<api_parameter>=<Wert>
const val BASE_URL = "https://api.jamendo.com/v3.0/albums/tracks/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface GotApiJamendoService {
    @GET("?client_id=43119550&format=json&limit=50&order=popularity_total")
    suspend fun getAlbum(): GotSong
}

object GotJamendoApi {
    val retrofitService: GotApiJamendoService by lazy { retrofit.create(GotApiJamendoService::class.java)}
}