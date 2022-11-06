package com.pg.trendinggithub

import android.util.TypedValue
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

    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bindTo(item: GithubRepo?, onItemClick: (id: Int) -> Unit, isSelected: Boolean) {

        if (isSelected) {
            itemView.setBackgroundResource(android.R.color.darker_gray)
        } else {
            val typedValue = TypedValue()
            itemView.context.theme.resolveAttribute(
                com.google.android.material.R.attr.colorOnSurfaceVariant,
                typedValue,
                true
            )
            val color = typedValue.data
            itemView.setBackgroundResource(color)
        }

        itemView.setOnClickListener {
            onItemClick(adapterPosition)
        }
        repoName.text = item?.fullName
        login.text = item?.owner?.login
        starForks.text = "${item?.starCounts} Stars ${item?.forkCounts} Forks"
        Glide.with(itemView.context).load(item?.owner?.avatarUrl).into(avatar)
    }
}
