package com.pg.trendinggithub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
}
