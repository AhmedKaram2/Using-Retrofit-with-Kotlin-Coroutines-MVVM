package com.populararticles.ui.articlesActivity.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.populararticles.R

import com.populararticles.data.models.response.ArticlesResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_popular_article.view.*


class PopularArticlesAdapter(private val articles: ArrayList<ArticlesResult>) :
    RecyclerView.Adapter<PopularArticlesAdapter.DataViewHolder>() {

    var onItemClick: ((ArticlesResult) -> Unit)? = null

   inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(article: ArticlesResult) {
            itemView.apply {
                list_title.text = article.title
                list_author.text = article.byline
                list_date.text = article.publishedDate

                itemView.setOnClickListener{
                    onItemClick?.invoke(article)
                }

                article.media?.let { media ->
                    if (media.isNotEmpty()) {
                        media[0].mediaMetadata?.let { metaData ->
                            if (metaData.isNotEmpty())
                                Picasso.get().load(metaData[0].url).into(list_image)
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_popular_article, parent, false)
        )

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    fun addArticles(articles: List<ArticlesResult>) {
        this.articles.apply {
            clear()
            addAll(articles)
        }

    }
}