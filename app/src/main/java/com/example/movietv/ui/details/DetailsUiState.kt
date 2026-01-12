package com.example.movietv.ui.details

import com.example.movietv.data.remote.dto.TitleDetailsDto

sealed class DetailsUiState {
    object Loading : DetailsUiState()
    data class Success(val data: TitleDetailsDto) : DetailsUiState()
    data class Error(val message: String) : DetailsUiState()
}
