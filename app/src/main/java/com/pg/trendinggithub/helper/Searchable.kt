package com.pg.trendinggithub.helper

import com.pg.trendinggithub.model.GithubRepo

interface Searchable {
    fun filterList(query: String, list: MutableList<GithubRepo>)
}
