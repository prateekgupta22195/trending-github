package com.pg.trendinggithub.model

import com.google.gson.annotations.SerializedName

data class GithubSearchResponse(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("incomplete_results") val inCompleteResults: Boolean,
    @SerializedName("items") val items: List<GithubRepo>,
)

data class GithubRepo(
    @SerializedName("id") val id: Int,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("name") val name: String,
    @SerializedName("owner") val owner: Owner,
    @SerializedName("stargazers_count") val starCounts: Int,
    @SerializedName("forks_count") val forkCounts: Int,
)

data class Owner(
    @SerializedName("id") val id: Int,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("login") val login: String,
)
