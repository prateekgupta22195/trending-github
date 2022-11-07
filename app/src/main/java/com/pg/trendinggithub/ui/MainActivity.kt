package com.pg.trendinggithub.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pg.trendinggithub.R
import com.pg.trendinggithub.vm.RepoListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: RepoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        init view model
        viewModel = ViewModelProvider(this)[RepoListViewModel::class.java]
//        setting up RecyclerView
        setRecyclerView()

//      observe for errors
        viewModel.errorMessage.observe(this) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(baseContext, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setRecyclerView() {
        findViewById<RecyclerView?>(R.id.rv_repos).apply {
            with(LinearLayoutManager(context)) {
                orientation = LinearLayoutManager.VERTICAL
                layoutManager = this
            }
            adapter = viewModel.repoAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val search: SearchView = menu!!.findItem(R.id.search).actionView as SearchView
        search.setSearchableInfo(manager.getSearchableInfo(componentName))

        search.setOnCloseListener {
            viewModel.clearSearchFilter()
            return@setOnCloseListener false
        }
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.filterList(query)
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                viewModel.filterList(query)
                return true
            }
        })
        return true
    }
}
