package com.example.powernapping.test

data class TestResponse(
    val headers: Headers,
    val results: List<Result>
)

data class Headers(
    val code: Int,
    val error_message: String,
    val next: String,
    val results_count: Int,
    val status: String,
    val warnings: String
)

data class Result(
    val artist_id: String,
    val artist_name: String,
    val id: String,
    val image: String,
    val name: String,
    val releasedate: String,
    val tracks: List<Track>,
    val zip: String,
    val zip_allowed: Boolean
)

data class Track(
    val audio: String,
    val audiodownload: String,
    val audiodownload_allowed: Boolean,
    val duration: String,
    val id: String,
    val license_ccurl: String,
    val name: String,
    val position: String
)