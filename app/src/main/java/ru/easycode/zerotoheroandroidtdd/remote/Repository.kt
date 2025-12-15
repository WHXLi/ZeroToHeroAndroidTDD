package ru.easycode.zerotoheroandroidtdd.remote

const val BASE_URL = "https://raw.githubusercontent.com/JohnnySC/ZeroToHeroAndroidTDD/task/"
const val TASK_URL = "018-clouddatasource/app/sampleresponse.json"

interface Repository {

    suspend fun load(): SimpleResponse

    class Base(
        private val service: SimpleService,
        private val url: String,
    ): Repository {

        override suspend fun load(): SimpleResponse {
            return service.fetch(url)
        }
    }
}