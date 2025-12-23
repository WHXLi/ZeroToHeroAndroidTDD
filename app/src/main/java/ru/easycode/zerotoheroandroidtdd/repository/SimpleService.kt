package ru.easycode.zerotoheroandroidtdd.repository

import retrofit2.http.GET
import retrofit2.http.Url

const val BASE_URL = "https://raw.githubusercontent.com/JohnnySC/ZeroToHeroAndroidTDD/task/"
const val TASK_URL = "018-clouddatasource/app/sampleresponse.json"

interface SimpleService {

    @GET
    suspend fun fetch(@Url url: String): SimpleResponse
}