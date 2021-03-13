package com.populararticles.data.repository

import com.populararticles.data.api.ApiHelper
import com.populararticles.data.models.response.PopularArticlesResponse
import com.populararticles.data.repositories.ArticlesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class ArticlesRepositoryTest {
    private val apiHelper = mock(ApiHelper::class.java)
    private val repo = ArticlesRepository(apiHelper)

    @Test
    fun getPopularsArticles_is_working() = runBlocking {
        val response = PopularArticlesResponse().apply {
            this.status = "success"
        }
        `when`(apiHelper.getPopularArticles()).thenReturn(response)

        val repoResponse = repo.getPopularsArticles()

        Assert.assertEquals("success", repoResponse.status)
    }
}