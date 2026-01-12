package com.example.movietv.data.remote

import com.example.movietv.data.remote.dto.TitleDetailsDto
import com.example.movietv.data.remote.dto.TitleResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WatchmodeApi {

    @GET("list-titles/")
    fun getMovies(
        @Query("apiKey") apiKey: String,
        @Query("types") types: String = "movie"
    ): Single<TitleResponse>

    @GET("list-titles/")
    fun getTvShows(
        @Query("apiKey") apiKey: String,
        @Query("types") types: String = "tv_series"
    ): Single<TitleResponse>

    @GET("title/{id}/details/")
    fun getTitleDetails(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String
    ): Single<TitleDetailsDto>

}
