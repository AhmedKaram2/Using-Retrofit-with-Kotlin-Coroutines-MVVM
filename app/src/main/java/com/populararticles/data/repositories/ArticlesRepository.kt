package com.populararticles.data.repositories

import com.populararticles.data.api.ApiHelper


class ArticlesRepository (private val apiHelper: ApiHelper) {

    suspend fun getPopularsArticles() = apiHelper.getPopularArticles()

}