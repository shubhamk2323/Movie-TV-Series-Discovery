package com.example.movietv.ui.home

import androidx.lifecycle.ViewModel
import com.example.movietv.data.repository.MovieRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    private val disposables = CompositeDisposable()

    init {
        fetchData()
    }

    fun refresh() {
        fetchData()
    }


    private fun fetchData() {
        _uiState.value = HomeUiState.Loading

        val disposable = repository.fetchMoviesAndTv()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { (movies, tvShows) ->
                    _uiState.value = HomeUiState.Success(movies, tvShows)
                },
                {  throwable ->
                    val message =
                        if (throwable is java.net.UnknownHostException) {
                            "No internet connection. Please check your network."
                        } else {
                            "Something went wrong. Please try again."
                        }

                    _uiState.value = HomeUiState.Error(message)

                }
            )

        disposables.add(disposable)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}
