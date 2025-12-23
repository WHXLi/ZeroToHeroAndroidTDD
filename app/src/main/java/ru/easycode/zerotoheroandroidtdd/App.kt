package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.easycode.zerotoheroandroidtdd.repository.BASE_URL
import ru.easycode.zerotoheroandroidtdd.repository.SimpleService

class App: Application() {

    val service: SimpleService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(SimpleService::class.java)
}