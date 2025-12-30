package ru.easycode.zerotoheroandroidtdd.wrapper

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface LiveDataWrapper<T> {

    val mutableLiveData: MutableLiveData<T>

    interface Observe<T> : LiveDataWrapper<T> {

        fun liveData(): LiveData<T>
    }

    interface Save<T>: LiveDataWrapper<T> {

        fun save(bundle: BundleWrapper.Save)
    }

    interface Update<T>: LiveDataWrapper<T> {

        fun update(param: T)
    }

    interface Mutable<T> : Observe<T>, Save<T>, Update<T>, LiveDataWrapper<T>
}