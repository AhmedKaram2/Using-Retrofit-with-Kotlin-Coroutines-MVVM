package com.populararticles.data.repositories

import com.mindorks.retrofit.coroutines.data.api.ApiHelper

class ArticlesRepository (private val apiHelper: ApiHelper) {

    suspend fun getPopularsArticles() = apiHelper.getPopularArticles()

}