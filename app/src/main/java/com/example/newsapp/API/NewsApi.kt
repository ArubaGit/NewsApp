package com.example.newsapp.API

data class NewsApi(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)