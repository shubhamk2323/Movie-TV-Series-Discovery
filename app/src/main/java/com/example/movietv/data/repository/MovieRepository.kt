package com.example.movietv.data.repository

import com.example.movietv.data.remote.WatchmodeApi
import com.example.movietv.data.remote.dto.TitleDetailsDto
import com.example.movietv.data.remote.dto.TitleDto
import com.example.movietv.utils.Constants
import io.reactivex.rxjava3.core.Single

class MovieRepository(
    private val api: WatchmodeApi
) {

    fun fetchMoviesAndTv(): Single<Pair<List<TitleDto>, List<TitleDto>>> {
        return Single.zip(
            api.getMovies(Constants.API_KEY),
            api.getTvShows(Constants.API_KEY)
        ) { moviesResponse, tvResponse ->
            Pair(
                moviesResponse.titles,
                tvResponse.titles
            )
        }
    }

    fun fetchTitleDetails(id: Int): Single<TitleDetailsDto> {
        return api.getTitleDetails(id, Constants.API_KEY)
    }

}
