package com.hank.ccm

//
data class Words(
    val words: List<Word>
)

data class Word(
    val difficulty: Int,
    val means: String,
    val name: String,
    val star: Int
)



// TMDB
data class MovieResult(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)

data class Movie(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)







