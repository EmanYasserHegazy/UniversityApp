package com.education.domain.model.remote

import com.google.gson.annotations.SerializedName

data class BaseResponse(val code: Int? = 0, val data: Data? = Data())

data class Data(
    val universities: List<University>? = listOf()
)

data class University(
    val name: String? = "",
    @SerializedName("state-province") val state: String? = "",
    val web_pages: List<String>? = listOf(),
    val country: String? = "",
    @SerializedName("alpha_two_code") val countryCode: String
)