package com.education.universitiesListFeature.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.education.universitiesListFeature.presentation.vm.UniversitiesListViewModel
import com.education.universitieslistfeature.databinding.FragmentUniversitiesListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UniversitiesListFragment : Fragment() {

    private lateinit var viewModel: UniversitiesListViewModel
    private lateinit var universitiesAdapter: UniversitiesAdapter

    private var binding: FragmentUniversitiesListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUniversitiesListBinding.inflate(inflater)
//        binding = DataBindingUtil.inflate(
//            inflater, R.layout.fragment_universities_list, container, false
//        )
        viewModel = ViewModelProvider(this)[UniversitiesListViewModel::class.java]
        binding.also {
            println("bindinng${binding?.universitiesViewModel}")
            it?.universitiesViewModel = viewModel
            it?.lifecycleOwner = this
        }
        universitiesAdapter = UniversitiesAdapter()
        binding?.universitiesRecycler?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = universitiesAdapter
        }

        viewModel.universitiesListLiveData.observe(viewLifecycleOwner) { universities ->
            universitiesAdapter.submitList(universities)
        }
        return binding?.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        viewModel = ViewModelProvider(this).get(UniversitiesListViewModel::class.java)
//        binding.also {
//            it?.universitiesViewModel = viewModel
//            it?.lifecycleOwner = this
//
//        }
//    }

}