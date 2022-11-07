package com.pg.trendinggithub

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pg.trendinggithub.model.GithubRepo

class RepositoryViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.repository_item, parent, false)
) {

    private val repoName = itemView.findViewById<TextView>(R.id.tv_repo_name)
    private val login = itemView.findViewById<TextView>(R.id.tv_login)
    private val avatar = itemView.findViewById<ImageView>(R.id.iv_avatar)
    private val starForks = itemView.findViewById<TextView>(R.id.tv_stars_forks)

    fun bindTo(item: GithubRepo, onItemClick: () -> Unit, selectedItemInfo: ItemInfo?) {

        if (selectedItemInfo?.githubRepo?.id == item.id)
            itemView.setBackgroundResource(android.R.color.darker_gray)
        else
            itemView.setBackgroundResource(0)

        itemView.setOnClickListener { onItemClick() }

        repoName.text = item.fullName
        login.text = item.owner.login
        starForks.text = "${item.starCounts} Stars ${item.forkCounts} Forks"
        Glide.with(itemView.context).load(item.owner.avatarUrl).into(avatar)
    }
}

/**
 * This class will hold the info for current selected repo.
 * So that if we need to update older selected item,
 * We can call notifyItemChanged with older position and
 * if we want to access Id or any other item info,
 * we can use {githubRepo}
 */
class ItemInfo(val position: Int, val githubRepo: GithubRepo)
