package com.pg.trendinggithub.data.remote

import com.pg.trendinggithub.model.GithubSearchResponse
import retrofit
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RemoteApi {

    @GET("/search/repositories")
    suspend fun getRepos(
        @Query("per_page") perPage: Int = 100,
        @Query("q") query: String = "since:today+sort:stars",
        @Header("Accept") key: String = "application/vnd.github+json",
    ): Response<GithubSearchResponse>
}

var client: RemoteApi = retrofit.create(RemoteApi::class.java)
