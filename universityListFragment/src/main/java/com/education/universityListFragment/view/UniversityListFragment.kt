package com.education.universityListFragment.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.education.domain.model.remote.University
import com.education.universityListFragment.databinding.FragmentUniversityListBinding
import com.education.universityListFragment.vm.UniversityListtViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UniversityListFragment : Fragment() {

    val viewModel by viewModels<UniversityListtViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        println("in UniversityListFragment")
        val binding = FragmentUniversityListBinding.inflate(inflater)
        binding.also {
            it.lifecycleOwner = viewLifecycleOwner
            it.universityListtViewModel = viewModel
        }

        val recyclerView = binding.universityListRecycler
        val adapter = UniversityListAdapter()
        recyclerView.adapter = adapter
        adapter.onUniversityClick { university ->
            viewModel.onUniversityClicked(university)
            navigateToDestinationFragment(university)
//
        }

        return binding.root
    }

    private fun navigateToDestinationFragment(university: University) {
        val deepLinkUri =
            Uri.parse("universityapp://universitydetails?countryName=${university.country}&countryCode=${university.countryCode}&state=${university.state}&universityName=${university.name}")
                .buildUpon()
                .appendQueryParameter("country", university.country)
                .build()

        requireActivity().startActivity(Intent(Intent.ACTION_VIEW, deepLinkUri))
    }
}