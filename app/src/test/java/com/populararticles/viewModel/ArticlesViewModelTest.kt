package com.populararticles.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.populararticles.data.api.ApiHelper
import com.populararticles.data.api.RetrofitBuilder
import com.populararticles.data.models.response.PopularArticlesResponse
import com.populararticles.data.repositories.ArticlesRepository
import com.populararticles.ui.articlesActivity.viewModel.ArticlesViewModel
import com.populararticles.utils.Resource
import com.populararticles.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ArticlesViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiHelper: ApiHelper

    @Mock
    private lateinit var apiArticlesObserver: Observer<Resource<PopularArticlesResponse>>

    @Before
    fun setUp() {
        // SetUp api helper before test
        apiHelper = ApiHelper(RetrofitBuilder.articlesCalls)
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            doReturn(PopularArticlesResponse())
                .`when`(apiHelper)
                .getPopularArticles()
            val viewModel = ArticlesViewModel(ArticlesRepository(ApiHelper(RetrofitBuilder.articlesCalls)))
            viewModel.getPopularsArticles().observeForever(apiArticlesObserver)
            verify(apiHelper).getPopularArticles()
            verify(apiArticlesObserver).onChanged(Resource.success(PopularArticlesResponse()))
            viewModel.getPopularsArticles().removeObserver(apiArticlesObserver)
        }
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Message For You"
            doThrow(RuntimeException(errorMessage))
                .`when`(apiHelper)
                .getPopularArticles()
            val viewModel = ArticlesViewModel(ArticlesRepository(ApiHelper(RetrofitBuilder.articlesCalls)))
            viewModel.getPopularsArticles().observeForever(apiArticlesObserver)
            verify(apiHelper).getPopularArticles()
            verify(apiArticlesObserver).onChanged(
                Resource.error(
                    data = null,
                    RuntimeException(errorMessage).toString(),
                )
            )
            viewModel.getPopularsArticles().removeObserver(apiArticlesObserver)
        }
    }

    @After
    fun tearDown() {
        // do something if required
    }

}