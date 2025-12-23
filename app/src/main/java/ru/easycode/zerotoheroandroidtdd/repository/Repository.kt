package ru.easycode.zerotoheroandroidtdd.repository

import android.util.Log
import java.net.UnknownHostException

interface Repository {

    suspend fun load(): LoadResult

    class Base(
        private val service: SimpleService,
        private val url: String,
    ): Repository {

        override suspend fun load(): LoadResult {
            return try {
                LoadResult.Success(service.fetch(url))
            } catch (e: UnknownHostException) {
                Log.e(javaClass.simpleName, "load: ", e )
                LoadResult.Error(true)
            } catch (e: Exception) {
                Log.e(javaClass.simpleName, "load: ", e )
                LoadResult.Error(false)
            }
        }
    }
}