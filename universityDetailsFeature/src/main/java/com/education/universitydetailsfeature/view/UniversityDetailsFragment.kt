package com.education.universitydetailsfeature.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.education.domain.model.remote.University
import com.education.universitydetailsfeature.databinding.FragmentUniversityDetailsBinding
import com.education.universitydetailsfeature.vm.UniversityDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UniversityDetailsFragment : Fragment() {

    val viewModel by viewModels<UniversityDetailsViewModel>()
    private lateinit var binding: FragmentUniversityDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUniversityDetailsBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val uri = requireActivity().intent.data
        val countryName = uri?.getQueryParameter("countryName")
        val countryCode = uri?.getQueryParameter("countryCode")
        val state = uri?.getQueryParameter("state")
        val universityName = uri?.getQueryParameter("universityName")
        val university =
            University(
                name = universityName ?: "",
                state = state ?: "",
                country = countryName ?: "",
                countryCode = countryCode ?: ""
            )
        println("emaaaan${university.name}")
        println("emaaaan${university.state}")
        println("emaaaan${university.country}")
        println("emaaaan${university.countryCode}")
        println("emaaaan${university.state?.equals("null")}")
        viewModel.getUniversityFromList(university)

        binding.iconRefresh.setOnClickListener {
            val deepLinkUri =
                Uri.parse("universityapp://fragmentlist")
                    .buildUpon()
                    .build()

            requireActivity().startActivity(Intent(Intent.ACTION_VIEW, deepLinkUri))
        }

    }

}