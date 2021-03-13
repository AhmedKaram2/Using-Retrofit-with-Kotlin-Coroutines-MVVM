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
        viewModel = ViewModelProviders.of(this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.articlesCalls))
        ).get(ArticlesViewModel::class.java)
    }

    // setup Recyclerview to be ready handle the articles items
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

    // when item clicked it the recycler --> set the data get from item to it's object in viewModel
    //start the fragment after the to use this item showing the details
    private fun handleRecyclerItemClicked(){

        adapter.onItemClick = { article ->

            viewModel.articlesResult = article
            showDetailsFragment()

        }
    }

    // set the fragment transaction and put the fragment in it's view then commit it
    private fun showDetailsFragment(){

        val myFragment = ArticlesListFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.details_fragment, myFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    // Observe The status of getting the data
    // if Status success hid progress and fill the recyclerview with the articles list
    // if error hide progress  and show the error message
    //if loading hid recycler and show the progress until the status change
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