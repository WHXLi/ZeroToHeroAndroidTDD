package ru.easycode.zerotoheroandroidtdd.core.livedata

import ru.easycode.zerotoheroandroidtdd.core.bundle.BundleWrapper

interface ListLiveDataWrapper: LiveDataWrapper {

    interface Read: LiveDataWrapper.Read<List<String>>

    interface Add {

        fun add(value: String)
    }

    interface Save {

        fun save(bundleWrapper: BundleWrapper.Save)
    }

    interface Mutable: LiveDataWrapper.Mutable<List<String>>, Save, Add

    class Base: LiveDataWrapper.Abstract<List<String>>(), Mutable {

        override fun save(bundleWrapper: BundleWrapper.Save) {
            liveData.value?.let {
                bundleWrapper.save(ArrayList(it))
            }
        }

        override fun add(value: String) {
            val currentList = liveData.value ?: emptyList()
            val newList = ArrayList(currentList)
            newList.add(value)
            liveData.postValue(newList)
        }
    }
}