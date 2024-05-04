package com.education.universityList.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.education.domain.model.remote.University
import com.education.domain.usecase.GetCountryUniversitiesUseCase
import com.education.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UniversityListViewModel @Inject constructor(private val countryUniversitiesUseCase: GetCountryUniversitiesUseCase) :
    ViewModel() {
    private var _universitiesListLiveData = MutableLiveData<List<University>?>()
    val universitiesListLiveData: MutableLiveData<List<University>?> = _universitiesListLiveData


    private val _navigateToUniversityDetail = MutableLiveData<University>()
    val navigateToUniversityDetail: LiveData<University> = _navigateToUniversityDetail


    init {
        // TODO should make text input and take the value from user
        getCountryUniversities("United Arab Emirates", "AE")
    }

    private var fetchJob: Job? = null

    private fun getCountryUniversities(country: String, countryCode: String) {
        fetchJob?.cancel()

        fetchJob = viewModelScope.launch {
            countryUniversitiesUseCase.invoke(country, countryCode).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _universitiesListLiveData.value = result?.data
                    }

                    is Resource.Error -> {
                        // TODO should show #Error screen
                        println("ErrorEman${result.message}")
                    }

                    is Resource.Loading -> {
                        // TODO should show #LOADING screen
                        println("loadingEman${result.message}")
                    }
                }
            }.launchIn(this)
        }
    }

    fun onUniversityClicked(university: University) {
        _navigateToUniversityDetail.value = university
    }
}