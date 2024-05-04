package com.education.domain.usecase

import com.education.domain.repo.UniversitiesRepository
import javax.inject.Inject

class GetCountryUniversitiesUseCase @Inject constructor(private val repository: UniversitiesRepository) {

    operator fun invoke(country: String, countryCode: String) =
        repository.getCountryUniversities(country, countryCode)
}