package com.education.domain.repo

import com.education.domain.model.remote.University
import com.education.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface UniversitiesRepository {
    fun getCountryUniversities(
        country: String,
        countryCode: String
    ): Flow<Resource<List<University>>>
}