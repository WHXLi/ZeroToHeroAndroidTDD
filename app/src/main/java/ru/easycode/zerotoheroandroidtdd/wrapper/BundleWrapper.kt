package ru.easycode.zerotoheroandroidtdd.wrapper

import android.os.Bundle

interface BundleWrapper {

    interface Save {

        fun save(list: ArrayList<CharSequence>)
    }

    interface Restore {

        fun restore(): List<CharSequence>
    }

    interface Mutable: Save, Restore

    class CharSequenceBundle(
        private val bundle: Bundle,
    ): Mutable {

        override fun save(list: ArrayList<CharSequence>) {
            bundle.putCharSequenceArrayList(CHAR_SEQUENCE_KEY, list)
        }

        override fun restore() = bundle.getCharSequenceArrayList(CHAR_SEQUENCE_KEY) as List<CharSequence>

        private companion object {
            private const val CHAR_SEQUENCE_KEY = "CHAR_SEQUENCE_KEY"
        }
    }
}