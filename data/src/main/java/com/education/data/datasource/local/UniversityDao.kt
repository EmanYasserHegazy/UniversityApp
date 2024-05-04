package com.education.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UniversityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUniversityList(universities: List<UniversityEntity>)
    
    @Query("SELECT * FROM universityentity")
    suspend fun getCountryUniversities(): List<UniversityEntity>
}