package com.education.universityListFragment.view

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.education.domain.model.remote.University


object UniversitiesBindingAdapters {

    @JvmStatic
    @BindingAdapter("app:addUniversitiesRecyclerItems")
    fun addUniversitiesRecyclerItems(
        recyclerView: RecyclerView, list: List<University>?,

    ) {

        list.let {
            if (recyclerView.adapter == null) {
                val adapter = UniversityListAdapter()
                recyclerView.adapter = adapter
                adapter.submitList(list)

            } else {
                val adapter = recyclerView.adapter as UniversityListAdapter
                adapter.submitList(list)

            }
//            recyclerView.adapter.onItemClick = { character ->
//                val action = CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment(character.name)
//                findNavController().navigate(action)
//            }
        }

    }

}
