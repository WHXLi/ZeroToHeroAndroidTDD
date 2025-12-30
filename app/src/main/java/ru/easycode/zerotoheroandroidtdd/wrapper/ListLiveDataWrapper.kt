package ru.easycode.zerotoheroandroidtdd.wrapper

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.utils.SingleLiveEvent

interface ListLiveDataWrapper<T>: LiveDataWrapper.Mutable<List<CharSequence>> {

    val list: ArrayList<T>

    fun add(new: T)

    class CharSequenceList: ListLiveDataWrapper<CharSequence> {

        override val mutableLiveData: MutableLiveData<List<CharSequence>> = SingleLiveEvent()
        override val list: ArrayList<CharSequence> = arrayListOf()

        override fun liveData(): LiveData<List<CharSequence>> {
            TODO("Not yet implemented")
        }

        override fun add(new: CharSequence) {
            TODO("Not yet implemented")
        }

        override fun save(bundle: BundleWrapper.Save) {
            TODO("Not yet implemented")
        }

        override fun update(param: List<CharSequence>) {
            TODO("Not yet implemented")
        }
    }
}