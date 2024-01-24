package com.example.nyt.data

data class TopArticles(
    val copyright: String,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)