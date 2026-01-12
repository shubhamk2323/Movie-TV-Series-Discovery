package com.example.movietv.ui.details

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import com.example.movietv.data.repository.MovieRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class DetailsViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val uiState: StateFlow<DetailsUiState> = _uiState

    private val disposables = CompositeDisposable()

    fun loadDetails(titleId: Int) {
        _uiState.value = DetailsUiState.Loading

        val disposable = repository.fetchTitleDetails(titleId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { details ->
                    _uiState.value = DetailsUiState.Success(details)
                },
                { error ->
                    _uiState.value =
                        DetailsUiState.Error(
                            error.message ?: "Something went wrong"
                        )
                }
            )

        disposables.add(disposable)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}
