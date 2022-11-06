package com.pg.trendinggithub.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pg.trendinggithub.RepositoryAdapter
import com.pg.trendinggithub.model.GithubRepo
import com.pg.trendinggithub.model.GithubSearchResponse
import com.pg.trendinggithub.service.client
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoListViewModel : ViewModel() {

    val data: MutableList<GithubRepo> = mutableListOf()

    val repoAdapter by lazy {
        RepositoryAdapter()
    }

    init {

        viewModelScope.launch(Dispatchers.IO) {
            client.getRepos().enqueue(object : Callback<GithubSearchResponse> {
                override fun onResponse(
                    call: Call<GithubSearchResponse>,
                    response: Response<GithubSearchResponse>,
                ) {
                    response.body()?.items?.let {
                        data.addAll(it)
                        repoAdapter.submitList(data)
                    }
                }

                override fun onFailure(call: Call<GithubSearchResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }
}
