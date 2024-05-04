package com.education.data.datasource.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}