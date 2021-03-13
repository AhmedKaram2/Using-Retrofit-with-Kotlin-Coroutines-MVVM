package com.populararticles.ui.articlesActivity.fragments

import android.content.Context
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
import kotlinx.android.synthetic.main.item_popular_article.view.*

class ArticlesListFragment : Fragment() {

    private var articlesViewModel: ArticlesViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_article_details, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articlesViewModel = ViewModelProviders.of(activity!!).get(ArticlesViewModel::class.java)
        articlesViewModel?.let {
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