package com.education.universitiesListFeature.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.education.domain.model.remote.University
import com.education.domain.usecase.GetCountryUniversitiesUseCase
import com.education.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UniversitiesListViewModel @Inject constructor(private val countryUniversitiesUseCase: GetCountryUniversitiesUseCase) :
    ViewModel() {
//    private var _universitiesData = MutableLiveData<List<University>?>()
//    val universitiesData: LiveData<List<University>?> = _universitiesData


        private var _universitiesListLiveData = MutableLiveData<List<University>>()
    val universitiesListLiveData: LiveData<List<University>> =
        _universitiesListLiveData

    val items: List<String> = listOf("Item 1", "Item 2", "Item 3")
    init {
        println("emaaaaaanyaaaaaererere")
        getCountryUniversities("United Arab Emirates", "AE")
    }

    private var fetchJob: Job? = null

    private fun getCountryUniversities(country: String, countryCode: String) {
        fetchJob?.cancel()

        fetchJob = viewModelScope.launch {
            countryUniversitiesUseCase.invoke(country, countryCode).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _universitiesListLiveData.value = arrayListOf(
                            University(
                                name = "cairo Univer",
                                state = "Cairo",
                                web_pages = emptyList(),
                                country = "Egypt",
                                countryCode = "CA"
                            ),
                            University(
                                name = "cairo Univer",
                                state = "Cairo",
                                web_pages = emptyList(),
                                country = "Egypt",
                                countryCode = "CA"
                            ),
                            University(
                                name = "cairo Univer",
                                state = "Cairo",
                                web_pages = emptyList(),
                                country = "Egypt",
                                countryCode = "CA"
                            ),
                            University(
                                name = "cairo Univer",
                                state = "Cairo",
                                web_pages = emptyList(),
                                country = "Egypt",
                                countryCode = "CA"
                            ),
                            University(
                                name = "cairo Univer",
                                state = "Cairo",
                                web_pages = emptyList(),
                                country = "Egypt",
                                countryCode = "CA"
                            )
                        )//result.data?.universities
                        // isLoading=false
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
            }.catch { print("") }.launchIn(this)
        }
    }
}