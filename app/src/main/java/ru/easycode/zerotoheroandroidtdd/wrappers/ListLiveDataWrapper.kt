package ru.easycode.zerotoheroandroidtdd.wrappers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface ListLiveDataWrapper {

    fun liveData(): LiveData<List<CharSequence>>

    fun add(new: CharSequence)

    fun save(bundle: BundleWrapper.Save)

    fun update(list: List<CharSequence>)

    class Base(
        private val mutableLiveData: MutableLiveData<List<CharSequence>> = MutableLiveData()
    ): ListLiveDataWrapper {

        private val textList = ArrayList<CharSequence>()

        override fun liveData(): LiveData<List<CharSequence>> = mutableLiveData

        override fun add(new: CharSequence) {
            textList.add(new)
            update(textList)
        }

        override fun save(bundle: BundleWrapper.Save) {
            bundle.save(textList)
        }

        override fun update(list: List<CharSequence>) {
            mutableLiveData.postValue(list)
        }
    }
}