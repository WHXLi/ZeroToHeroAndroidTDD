package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface ListLiveDataWrapper {

    interface Read {

        fun liveData(): LiveData<List<String>>
    }

    interface Add {

        fun add(value: String)
    }

    interface Update {

        fun update(value: List<String>)
    }

    interface Mutable: Read, Add, Update



    class Base: Mutable {

        private val mutableLiveData: MutableLiveData<List<String>> = MutableLiveData()

        override fun liveData(): LiveData<List<String>> = mutableLiveData

        override fun add(value: String) {
            val newList = mutableLiveData.value?.toMutableList().apply { add(value) }
            mutableLiveData.postValue(newList)
        }

        override fun update(value: List<String>) {
            mutableLiveData.postValue(value)
        }
    }
}