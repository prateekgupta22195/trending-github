package com.pg.trendinggithub.data.remote

import com.pg.trendinggithub.model.GithubSearchResponse
import com.pg.trendinggithub.network.NetworkResult
import com.pg.trendinggithub.network.handleApi

class RemoteAPIImpl(private val posterService: RemoteApi) {
    suspend operator fun invoke(): NetworkResult<GithubSearchResponse> =
        handleApi { posterService.getRepos() }
}
