package com.education.data.repo

import com.education.data.datasource.local.UniversityDao
import com.education.data.datasource.mapper.UniversityDtoToEntityMapper
import com.education.data.datasource.mapper.UniversityEntityToDtoMapper
import com.education.data.datasource.remote.UniversityApi
import com.education.data.di.UniversityDtoToEntityMapperQualifier
import com.education.data.di.UniversityEntityToDtoMapperQualifier
import com.education.domain.model.remote.Data
import com.education.domain.model.remote.University
import com.education.domain.repo.UniversitiesRepository
import com.education.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UniversitiesRepositoryImpl @Inject constructor(
    private val api: UniversityApi,
    private val dao: UniversityDao,
    @UniversityDtoToEntityMapperQualifier private val dtoToEntityMapper: UniversityDtoToEntityMapper,
    @UniversityEntityToDtoMapperQualifier private val entityToDtoMapper: UniversityEntityToDtoMapper
) : UniversitiesRepository {
    lateinit var universitiesData: Data
    override fun getCountryUniversities(
        country: String, countryCode: String
    ): Flow<Resource<Data>> = flow {
        emit(Resource.Loading())
        val universities = dao.getCountryUniversities(countryCode = countryCode)
        universitiesData = Data(universities = entityToDtoMapper.mapList(universities))
        emit(Resource.Loading(data = universitiesData))
        try {
            val remoteUniversities = api.getCountryUniversities(country)
            dao.insertUniversityList(dtoToEntityMapper.mapList(remoteUniversities))
        } catch (e: HttpException) {
            universitiesData = Data(universities = entityToDtoMapper.mapList(universities))
            emit(
                Resource.Error(
                    message = "Oops, an error has been occured",
                    data = universitiesData
                )
            )

        } catch (e: IOException) {
            e.printStackTrace()
            println("exception${e.message}")
            println("exception${e.cause}")
            println("exception${e.localizedMessage}")
            universitiesData = Data(universities = entityToDtoMapper.mapList(universities))

            emit(
                Resource.Error(
                    message = "Can't Reach Server, please check the network connection",
                    data = universitiesData
                )
            )
        }

        val universitiesData = dao.getCountryUniversities(countryCode)
        emit(Resource.Success(Data(entityToDtoMapper.mapList(universitiesData))))
    }

    override fun getUniversityDetails(
        countryCode: String
    ): Flow<Resource<University>> = flow {
        emit(Resource.Loading())
        val universityDetails: University? =
            universitiesData.universities?.filter { it.countryCode === countryCode }?.get(0)
        universityDetails?.let {

            emit(Resource.Success(universityDetails))
        } ?: emit(Resource.Error(message = "error while getting the university details "))
    }
}