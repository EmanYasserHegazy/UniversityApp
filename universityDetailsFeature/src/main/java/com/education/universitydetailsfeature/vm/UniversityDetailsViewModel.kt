package com.education.universitydetailsfeature.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.education.domain.model.remote.University
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UniversityDetailsViewModel @Inject constructor() :
    ViewModel() {
    private var _universityListLiveData = MutableLiveData<University?>()
    val universityListLiveData: MutableLiveData<University?> = _universityListLiveData

    fun getUniversityFromList(university: University) {
        _universityListLiveData.value = university
    }
}