package com.education.data.datasource.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UniversityEntity(
    @PrimaryKey val countryCode:String,
    @ColumnInfo(name = "country_name") val name:String?,
    @ColumnInfo(name="state_province") val state:String?,
    @ColumnInfo(name="country") val country:String?,
    @ColumnInfo(name="web_pages") val webPages:List<String>?
)
