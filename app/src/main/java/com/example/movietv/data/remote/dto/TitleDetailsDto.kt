package com.example.movietv.data.remote.dto

data class TitleDetailsDto(
    val id: Int,
    val title: String,
    val year: Int?,
    val plot_overview: String?,
    val poster: String?
)
