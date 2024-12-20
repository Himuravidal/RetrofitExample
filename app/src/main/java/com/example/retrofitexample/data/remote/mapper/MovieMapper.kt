package com.example.retrofitexample.data.remote.mapper

import com.example.retrofitexample.data.remote.dto.MovieDto
import com.example.retrofitexample.domain.model.Movie

class MovieMapper {

    fun toDomain(dto: MovieDto): Movie =
        Movie(
            id = dto.id,
            adult = dto.adult,
            backdropPath = dto.backdropPath,
            genreIds = dto.genreIds,
            originalLanguage = dto.originalLanguage,
            originalTitle = dto.originalTitle,
            overview = dto.overview,
            popularity = dto.popularity,
            posterPath = dto.posterPath,
            releaseDate = dto.releaseDate,
            title = dto.title,
            video = dto.video,
            voteAverage = dto.voteAverage,
            voteCount = dto.voteCount,
            posterUrl = dto.posterPath?.let {
                "https://image.tmdb.org/t/p/w500$it"
            }
        )
}