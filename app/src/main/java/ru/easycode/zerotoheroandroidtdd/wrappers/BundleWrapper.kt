package ru.easycode.zerotoheroandroidtdd.wrappers

import android.os.Bundle

private const val KEY_TEXT_LIST = "KEY_TEXT_LIST"

interface BundleWrapper {

    interface Save: BundleWrapper {

        fun save(list: ArrayList<CharSequence>)
    }

    interface Restore: BundleWrapper {

        fun restore(): List<CharSequence>
    }

    interface Mutable: Save, Restore

    class Base(private val bundle: Bundle): Mutable {

        override fun save(list: ArrayList<CharSequence>) {
            bundle.putCharSequenceArrayList(KEY_TEXT_LIST, list)
        }

        override fun restore(): List<CharSequence> =
            bundle.getCharSequenceArrayList(KEY_TEXT_LIST) ?: emptyList()
    }
}