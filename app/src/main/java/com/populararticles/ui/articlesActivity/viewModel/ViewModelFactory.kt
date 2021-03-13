package com.populararticles.ui.articlesActivity.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mindorks.retrofit.coroutines.data.api.ApiHelper
import com.populararticles.data.repositories.ArticlesRepository

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticlesViewModel::class.java)) {
            return ArticlesViewModel(ArticlesRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}

