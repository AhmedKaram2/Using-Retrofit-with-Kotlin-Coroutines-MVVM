package com.populararticles.ui.articlesActivity.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.populararticles.R
import com.populararticles.data.api.ApiHelper
import com.populararticles.data.api.RetrofitBuilder
import com.populararticles.data.models.response.ArticlesResult
import com.populararticles.ui.articlesActivity.adapters.PopularArticlesAdapter
import com.populararticles.ui.articlesActivity.view.fragments.ArticlesListFragment
import com.populararticles.ui.articlesActivity.viewModel.ArticlesViewModel
import com.populararticles.ui.articlesActivity.viewModel.ViewModelFactory
import com.populararticles.utils.Status
import kotlinx.android.synthetic.main.activity_popular_articles.*

class ArticlesLisActivity : AppCompatActivity() {

    private lateinit var viewModel: ArticlesViewModel
    private lateinit var adapter: PopularArticlesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_articles)

        setupViewModel()
        setupUI()
        setupObservers()
        handleRecyclerItemClicked()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.articlesCalls))
        ).get(ArticlesViewModel::class.java)
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PopularArticlesAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun handleRecyclerItemClicked(){

        adapter.onItemClick = { article ->

            viewModel.articlesResult = article
            showDetailsFragment()

        }
    }

    private fun showDetailsFragment(){
        val myfragment = ArticlesListFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.details_fragment, myfragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun setupObservers() {
        viewModel.getPopularsArticles().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { articles -> retrieveList(articles.articlesResults!!) }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(articles: List<ArticlesResult>) {
        adapter.apply {
            addArticles(articles)
            notifyDataSetChanged()
        }
    }

}