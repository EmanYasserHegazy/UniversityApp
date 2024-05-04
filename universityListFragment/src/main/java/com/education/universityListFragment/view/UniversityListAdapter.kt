package com.education.universityListFragment.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.education.domain.model.remote.University
import com.education.universityListFragment.R
import com.education.universityListFragment.databinding.UniversityListItemBinding


class UniversityListAdapter() :
    ListAdapter<University, UniversityListAdapter.UniversityViewHolder>(DiffUtilsCallback()) {
    var onItemClick: ((University) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UniversityViewHolder(
        UniversityListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )


    override fun onBindViewHolder(holder: UniversityViewHolder, position: Int) {
        if (currentList.isNotEmpty()) {
            holder.bind(currentList[position])
            holder.itemView.setOnClickListener {
                println("currentlistItem${currentList[position]}")
                val university= currentList[position]
                onItemClick?.let { it(university) }
            }
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    fun onUniversityClick(listener: (University) -> Unit) {
        onItemClick = listener
    }

    inner class UniversityViewHolder(
        private val binding: UniversityListItemBinding,

    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: University) {
            binding.universityModel = item
            binding.root.setTag(R.id.university_item, item)
            binding.executePendingBindings()
        }
    }

    class DiffUtilsCallback : DiffUtil.ItemCallback<University>() {
        override fun areItemsTheSame(oldItem: University, newItem: University): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: University, newItem: University): Boolean {
            return oldItem == newItem
        }
    }
}
