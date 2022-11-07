package com.pg.trendinggithub.helper

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.pg.trendinggithub.ItemInfo
import com.pg.trendinggithub.RepositoryViewHolder
import com.pg.trendinggithub.model.GithubRepo
import com.pg.trendinggithub.util.containsIgnoreCase

class RepositoryAdapter(private var selectItemInfo: ItemInfo? = null) :

    ListAdapter<GithubRepo, RepositoryViewHolder>(diffCallback), Searchable {

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repo: GithubRepo = getItem(position)
        holder.bindTo(repo, {
            notifySelectedViews(ItemInfo(position, repo))
        }, selectItemInfo)
    }

    private fun notifySelectedViews(newlySelectedItemInfo: ItemInfo) {
        val oldPosition = selectItemInfo?.position ?: NO_POSITION
        selectItemInfo = newlySelectedItemInfo

//        Notifying older view to get unselected
        if (oldPosition != NO_POSITION)
            notifyItemChanged(oldPosition)

//        Notifying new item view to display selection
        notifyItemChanged(newlySelectedItemInfo.position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(parent)
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<GithubRepo>() {
            override fun areItemsTheSame(
                oldItem: GithubRepo,
                newItem: GithubRepo,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: GithubRepo,
                newItem: GithubRepo,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemCount(): Int {
        return currentList.count()
    }

    override fun filterList(query: String, list: MutableList<GithubRepo>) {
        list.filter {
            it.name.containsIgnoreCase(query) || it.owner.login.containsIgnoreCase(query)
        }.also {
            submitList(it)
        }
    }
}
