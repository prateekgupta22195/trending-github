package com.pg.trendinggithub.service

import com.pg.trendinggithub.model.GithubSearchResponse
import retrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RemoteApi {

    @GET("/search/repositories")
    fun getRepos(
        @Query("per_page") perPage: Int = 100,
        @Query("q") query: String = "since:today+sort:stars",
        @Header("Accept") key: String = "application/vnd.github+json",
    ): Call<GithubSearchResponse>
}

var client: RemoteApi = retrofit.create(RemoteApi::class.java)
