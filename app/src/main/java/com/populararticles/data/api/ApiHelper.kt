package com.mindorks.retrofit.coroutines.data.api

import com.populararticles.data.api.ArticlesCalls

class ApiHelper(private val articlesCalls: ArticlesCalls) {
    suspend fun getPopularArticles() = articlesCalls.getPopularArticles()

}