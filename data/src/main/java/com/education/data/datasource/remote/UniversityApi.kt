package com.education.data.datasource.remote

import com.education.domain.model.remote.Data
import com.education.domain.model.remote.University
import retrofit2.http.GET
import retrofit2.http.Query

interface UniversityApi {

    //todo check ? on search
    @GET("/search")
    suspend fun getCountryUniversities(
        @Query("country") country:String
    ):List<University>?
}