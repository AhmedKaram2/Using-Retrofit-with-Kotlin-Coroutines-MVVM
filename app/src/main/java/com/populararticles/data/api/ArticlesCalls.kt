package com.populararticles.data.api

import com.populararticles.data.models.response.PopularArticlesResponse
import retrofit2.http.GET

interface ArticlesCalls {

    @GET("svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=Y3ZxUuh4zzfgNFkSbzPxCOlENiyvoln3")
    suspend fun getPopularArticles(): PopularArticlesResponse

}