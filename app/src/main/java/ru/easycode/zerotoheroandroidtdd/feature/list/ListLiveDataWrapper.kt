package ru.easycode.zerotoheroandroidtdd.feature.list

import ru.easycode.zerotoheroandroidtdd.core.bundle.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.core.livedata.LiveDataWrapper

interface ListLiveDataWrapper: LiveDataWrapper {
    interface Add {
        fun add(source: CharSequence)
    }

    interface Save {
        fun save(bundleWrapper: BundleWrapper.Save)
    }

    interface Read: LiveDataWrapper.Read<List<CharSequence>>

    interface Mutable: LiveDataWrapper.Mutable<List<CharSequence>>, Save

    interface All: Mutable, Add, Save

    class Base : LiveDataWrapper.Abstract<List<CharSequence>>(), All  {
        override fun add(source: CharSequence) {
            val currentList = liveData.value ?: ArrayList()
            val newList = ArrayList(currentList)
            newList.add(source)
            liveData.value = newList
        }

        override fun save(bundleWrapper: BundleWrapper.Save) {
            liveData.value?.let { bundleWrapper.save(ArrayList(it)) }
        }
    }
}