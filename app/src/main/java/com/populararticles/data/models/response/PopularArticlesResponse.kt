package com.populararticles.data.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PopularArticlesResponse : Serializable {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("copyright")
    @Expose
    var copyright: String? = null

    @SerializedName("num_results")
    @Expose
    var numResults:Long = 0

    @SerializedName("results")
    @Expose
    var articlesResults: List<ArticlesResult>? = null
}