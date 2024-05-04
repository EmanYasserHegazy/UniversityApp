package com.education.data.datasource.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.education.data.datasource.util.JsonParser
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromMeaningsJson(json: String): List<String> {
        return jsonParser.fromJson<ArrayList<String>>(
            json,
            object : TypeToken<ArrayList<String>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toMeaningsJson(meanings: List<String>): String {
        return jsonParser.toJson(
            meanings,
            object : TypeToken<ArrayList<String>>(){}.type
        ) ?: "[]"
    }
}