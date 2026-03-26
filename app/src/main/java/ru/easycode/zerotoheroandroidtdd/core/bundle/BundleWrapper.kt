package ru.easycode.zerotoheroandroidtdd.core.bundle

import android.os.Bundle

private const val KEY_LIST = "KEY_LIST"

interface BundleWrapper {
    interface Save {
        fun save(list: ArrayList<CharSequence>)
    }

    interface Restore {
        fun restore(): List<CharSequence>
    }

    interface Mutable: Save, Restore

    class Base(
        private val bundle: Bundle,
    ): Mutable {
        override fun save(list: ArrayList<CharSequence>) {
            bundle.putCharSequenceArrayList(KEY_LIST, list)
        }

        override fun restore(): List<CharSequence> {
            return bundle.getCharSequenceArrayList(KEY_LIST) as List<CharSequence>
        }

    }
}