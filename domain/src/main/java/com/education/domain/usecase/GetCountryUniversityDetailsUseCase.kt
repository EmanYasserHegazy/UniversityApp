package com.education.domain.usecase

import com.education.domain.repo.UniversitiesRepository
import javax.inject.Inject

class GetCountryUniversityDetailsUseCase @Inject constructor(private val repository: UniversitiesRepository) {
    operator fun invoke(countryCode: String) =
        repository.getUniversityDetails(countryCode = countryCode)
}