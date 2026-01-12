package com.example.movietv.ui.home

import com.example.movietv.data.remote.dto.TitleDto

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(
        val movies: List<TitleDto>,
        val tvShows: List<TitleDto>
    ) : HomeUiState()

    data class Error(val message: String) : HomeUiState()
}
