package com.example.movietv.data.remote.dto

data class TitleResponse(
    val titles: List<TitleDto>
)

data class TitleDto(
    val id: Int,
    val title: String,
    val year: Int?,
    val imdb_id: String?,
    val tmdb_id: Int?,
    val tmdb_type: String?,
    val type: String?
)

