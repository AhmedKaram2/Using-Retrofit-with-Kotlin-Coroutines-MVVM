package com.populararticles.data.api

import com.populararticles.data.models.response.PopularArticlesResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class ApiHelperTest {
    private val calls = Mockito.mock(ArticlesCalls::class.java)
    private val apiHelper = ApiHelper(calls)

    @Test
    fun getPopularArticles_is_working() = runBlocking {
        val response = PopularArticlesResponse().apply {
            this.status = "success"
        }
        Mockito.`when`(calls.getPopularArticles()).thenReturn(response)

        val repoResponse = apiHelper.getPopularArticles()

        Assert.assertEquals("success", repoResponse.status)
    }
}