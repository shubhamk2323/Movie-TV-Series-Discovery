package com.example.movietv.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movietv.data.remote.RetrofitProvider
import com.example.movietv.data.repository.MovieRepository

class DetailsViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            val api = RetrofitProvider.provideApi()
            val repository = MovieRepository(api)
            return DetailsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
