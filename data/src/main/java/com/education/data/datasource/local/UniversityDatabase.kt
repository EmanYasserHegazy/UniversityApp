package com.education.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [UniversityEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class UniversityDatabase : RoomDatabase() {
    abstract val universityDao: UniversityDao
}