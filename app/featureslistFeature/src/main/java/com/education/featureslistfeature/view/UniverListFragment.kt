package com.education.featureslistfeature.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.education.featureslistfeature.R

class UniverListFragment : Fragment() {

    companion object {
        fun newInstance() = UniverListFragment()
    }

    private lateinit var viewModel: UniverListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, Fragment())
            .addToBackStack(null)
            .commit()
        return inflater.inflate(R.layout.fragment_univer_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UniverListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}