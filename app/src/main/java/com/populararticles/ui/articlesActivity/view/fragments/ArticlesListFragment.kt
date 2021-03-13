package com.populararticles.ui.articlesActivity.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.populararticles.R
import com.populararticles.data.models.response.ArticlesResult
import com.populararticles.ui.articlesActivity.viewModel.ArticlesViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_article_details.*

class ArticlesListFragment : Fragment() {

    private var articlesViewModel: ArticlesViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_article_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // connect with viewModel
        articlesViewModel = ViewModelProviders.of(activity!!).get(ArticlesViewModel::class.java)
        articlesViewModel?.let {
            // get data from view model when the fragment created
            setDataInViews(it.articlesResult)
        }
    }

    private fun setDataInViews(result: ArticlesResult) {

        details_title.text = result.title
        details_author.text = result.byline
        details_date.text = result.publishedDate
        details_details.text = result.abstract

        result.media?.let { media ->
            if (media.isNotEmpty()) {
                media[0].mediaMetadata?.let { metaData ->
                    if (metaData.isNotEmpty())
                        Picasso.get().load(metaData[2].url).into(details_image)
                }
            }
        }
    }

}