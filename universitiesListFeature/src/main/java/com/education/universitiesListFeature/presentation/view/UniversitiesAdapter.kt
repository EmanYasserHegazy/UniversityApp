package com.education.universitiesListFeature.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.education.domain.model.remote.University
import com.education.universitieslistfeature.databinding.UniversityItemListBinding

class UniversitiesAdapter :
    ListAdapter<University, UniversitiesAdapter.UniversityViewHolder>(DiffUtilsCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityViewHolder {
        return UniversityViewHolder(
            UniversityItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UniversityViewHolder, position: Int) {
        if (currentList.isNotEmpty()) {
            holder.bind(currentList[position])
        }
    }

    class UniversityViewHolder(
        private val binding: UniversityItemListBinding,
//        private val onReviewClick: ((appointments: Appointment) -> Unit)?,
//        private val onRequestAgainClick: ((appointments: Appointment) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: University) {

            binding.universityModel = item

//            binding.reviewButton.setOnClickListener {
//                onReviewClick?.invoke(item)
//            }
//            binding.requestAgainButton.setOnClickListener {
//                onRequestAgainClick?.invoke(item)
//            }
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
