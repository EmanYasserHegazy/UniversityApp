package com.education.data.di

import android.app.Application
import androidx.room.Room
import com.education.data.datasource.local.Converters
import com.education.data.datasource.local.UniversityDatabase
import com.education.data.datasource.mapper.UniversityDtoToEntityMapper
import com.education.data.datasource.mapper.UniversityEntityToDtoMapper
import com.education.data.datasource.remote.UniversityApi
import com.education.data.datasource.util.GsonParser
import com.education.data.repo.UniversitiesRepositoryImpl
import com.education.domain.repo.UniversitiesRepository
import com.education.domain.util.Constants.BASE_URL
import com.education.domain.util.Constants.TIMEOUT_CONNECT
import com.education.domain.util.Constants.TIMEOUT_READ
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UniversityModule {

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun provideOkHttpClient(

    ): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.connectTimeout(TIMEOUT_CONNECT.toLong(), TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(TIMEOUT_READ.toLong(), TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(TIMEOUT_READ.toLong(), TimeUnit.SECONDS)
        return okHttpBuilder.build()
    }

    @Singleton
    @Provides
    fun provideGsonFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gsonFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(gsonFactory)
            .client(okHttpClient).build()

    }

    @Provides
    @Singleton
    fun provideUniversityApi(retrofit: Retrofit): UniversityApi {
        return retrofit.create(UniversityApi::class.java)
    }

    @Provides
    @Singleton
    @UniversityEntityToDtoMapperQualifier
    fun provideGetUniversityUseCase(repository: UniversitiesRepository): UniversityEntityToDtoMapper {
        return UniversityEntityToDtoMapper()
    }

    @Provides
    @Singleton
    @UniversityDtoToEntityMapperQualifier
    fun provideUniversityDtoToEntityMapper(): UniversityDtoToEntityMapper {
        return UniversityDtoToEntityMapper()
    }

    @Provides
    @Singleton
    fun provideUniversityRepository(
        db: UniversityDatabase,
        api: UniversityApi,
        dtoToEntityMapper: UniversityDtoToEntityMapper,
        entityToDtoMapper: UniversityEntityToDtoMapper
    ): UniversitiesRepository {
        return UniversitiesRepositoryImpl(
            api, db.universityDao, dtoToEntityMapper, entityToDtoMapper
        )
    }

    @Provides
    @Singleton
    fun provideUniversityDatabase(app: Application): UniversityDatabase {
        return Room.databaseBuilder(
            app, UniversityDatabase::class.java, "university_db"
        ).addTypeConverter(Converters(GsonParser(Gson()))).build()
    }

}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class UniversityDtoToEntityMapperQualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class UniversityEntityToDtoMapperQualifier