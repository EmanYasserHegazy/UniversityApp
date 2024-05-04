package com.education.data.datasource.mapper

import com.education.data.datasource.local.UniversityEntity
import com.education.domain.model.remote.Data
import com.education.domain.model.remote.University
import javax.inject.Inject

class UniversityDtoToEntityMapper @Inject constructor(): Mapper<University, UniversityEntity> {
    override fun map(input: University): UniversityEntity {
        return UniversityEntity(
            countryCode = input.countryCode,
            name = input.name,
            country = input.country,
            webPages = input.web_pages,
            state = input.state
        )
    }

    fun mapList(inputList: List<University>?): List<UniversityEntity> {

        val outputList = mutableListOf<UniversityEntity>()

        inputList?.forEach { university ->
            outputList.add(map(university))
        }

        return outputList
    }
}