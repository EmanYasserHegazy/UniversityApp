package com.education.domain.di

import com.education.domain.repo.UniversitiesRepository
import com.education.domain.usecase.GetCountryUniversitiesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UniversityDomainModule {
    @Provides
    @Singleton
    fun provideGetUniversityUseCase(repository: UniversitiesRepository): GetCountryUniversitiesUseCase {
        return GetCountryUniversitiesUseCase(repository)
    }
}