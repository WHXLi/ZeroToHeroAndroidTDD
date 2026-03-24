package ru.easycode.zerotoheroandroidtdd.core.bundle

interface BundleWrapper {
    interface Save {
        fun save(list: ArrayList<CharSequence>)
    }

    interface Restore {
        fun restore(): List<CharSequence>
    }

    interface Mutable: Save, Restore
}