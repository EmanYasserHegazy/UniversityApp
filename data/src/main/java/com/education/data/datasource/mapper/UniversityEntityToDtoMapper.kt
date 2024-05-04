package com.education.data.datasource.mapper

import com.education.data.datasource.local.UniversityEntity
import com.education.domain.model.remote.University
import javax.inject.Inject

class UniversityEntityToDtoMapper @Inject constructor() : Mapper<UniversityEntity, University> {
    override fun map(input: UniversityEntity) = University(
        name = input.name,
        state = input.state,
        web_pages = input.webPages,
        country = input.country,
        countryCode = input.countryCode
    )

    fun mapList(input: List<UniversityEntity>): List<University> {
        val outputList = mutableListOf<University>()
        input.forEach { universityEntity ->
            outputList.add(map(universityEntity))
        }
        return outputList
    }
}