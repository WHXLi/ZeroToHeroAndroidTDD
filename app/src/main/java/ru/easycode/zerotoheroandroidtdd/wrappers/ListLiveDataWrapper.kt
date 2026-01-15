package ru.easycode.zerotoheroandroidtdd.wrappers

import androidx.lifecycle.LiveData

interface ListLiveDataWrapper {

    fun liveData(): LiveData<List<CharSequence>>

    fun add(new: CharSequence)

    fun save(bund)
}