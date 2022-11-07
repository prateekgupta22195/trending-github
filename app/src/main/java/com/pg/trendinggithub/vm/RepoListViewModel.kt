package com.pg.trendinggithub.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pg.trendinggithub.data.remote.RemoteAPIImpl
import com.pg.trendinggithub.data.remote.client
import com.pg.trendinggithub.helper.RepositoryAdapter
import com.pg.trendinggithub.model.GithubRepo
import com.pg.trendinggithub.network.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepoListViewModel : ViewModel() {

    private val data: MutableList<GithubRepo> = mutableListOf()
    val errorMessage = MutableLiveData<String?>(null)

    val repoAdapter by lazy {
        RepositoryAdapter()
    }

    private val remoteImpl by lazy {
        RemoteAPIImpl(client)
    }

//    TODO: we can move this part to repository later on
    init {
        viewModelScope.launch {
            when (val response = remoteImpl.invoke()) {
                is NetworkResult.Success -> {
                    data.addAll(response.data.items)
                    repoAdapter.submitList(data)
                }
                is NetworkResult.Error -> {
                    errorMessage.value = response.message
                }
                is NetworkResult.Exception -> {
                    errorMessage.value = response.e.message
                }
            }
        }
    }

    fun filterList(query: String) {
//        This task will be handled to worker thread to avoid main thread blocking
        viewModelScope.launch(Dispatchers.Default) {
            repoAdapter.filterList(query, data)
        }
    }

    fun clearSearchFilter() {
        repoAdapter.submitList(data)
    }
}
