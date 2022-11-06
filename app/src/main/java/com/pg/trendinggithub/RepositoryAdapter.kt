package com.pg.trendinggithub

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.pg.trendinggithub.model.GithubRepo

class RepositoryAdapter(private var selectedPosition: Int? = null) :
    ListAdapter<GithubRepo, RepositoryViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repo: GithubRepo = getItem(position)
        holder.bindTo(repo, { newPosition ->
            val oldPosition = selectedPosition
            selectedPosition = newPosition
            oldPosition?.let { notifyItemChanged(oldPosition) }
            notifyItemChanged(newPosition)
        }, position == selectedPosition)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(parent)
    }

    companion object {
        /**
         * This diff callback informs the PagedListAdapter how to compute list differences when new
         * PagedLists arrive.
         *
         * When you add a Repository with the 'Add' button, the PagedListAdapter uses diffCallback to
         * detect there's only a single item difference from before, so it only needs to animate and
         * rebind a single view.
         *
         * @see DiffUtil
         */
        val diffCallback = object : DiffUtil.ItemCallback<GithubRepo>() {
            override fun areItemsTheSame(
                oldItem: GithubRepo,
                newItem: GithubRepo,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            /**
             * Note that in kotlin, == checking on data classes compares all contents, but in Java,
             * typically you'll implement Object#equals, and use it to compare object contents.
             */
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
}
