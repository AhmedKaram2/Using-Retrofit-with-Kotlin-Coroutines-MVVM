package com.populararticles.data.api

class ApiHelper(private val articlesCalls: ArticlesCalls) {
    suspend fun getPopularArticles() = articlesCalls.getPopularArticles()

}