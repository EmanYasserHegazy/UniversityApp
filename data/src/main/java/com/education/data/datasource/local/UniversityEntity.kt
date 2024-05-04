package com.education.data.datasource.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UniversityEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "alpha_two_code") val countryCode: String?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "state_province") val state: String?,
    @ColumnInfo(name = "country") val country: String?,
    @ColumnInfo(name = "web_pages") val webPages: List<String>?
)
