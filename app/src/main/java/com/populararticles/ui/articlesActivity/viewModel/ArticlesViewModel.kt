package com.populararticles.ui.articlesActivity.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.populararticles.data.models.response.ArticlesResult
import com.populararticles.data.repositories.ArticlesRepository
import com.populararticles.utils.Resource
import kotlinx.coroutines.Dispatchers


class ArticlesViewModel(private val articlesRepository: ArticlesRepository) : ViewModel() {

    var articlesResult = ArticlesResult()

    fun getPopularsArticles() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = articlesRepository.getPopularsArticles()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}