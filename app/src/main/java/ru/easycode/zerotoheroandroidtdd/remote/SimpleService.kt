package ru.easycode.zerotoheroandroidtdd.remote

import retrofit2.http.GET
import retrofit2.http.Url

interface SimpleService {

    @GET
    suspend fun fetch(@Url url: String): SimpleResponse
}

data class SimpleResponse(
    val text: String? = null
)