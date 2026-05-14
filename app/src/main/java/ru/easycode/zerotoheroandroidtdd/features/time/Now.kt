package ru.easycode.zerotoheroandroidtdd.features.time

interface Now {

    fun nowMillis(): Long



    class Base: Now {

        override fun nowMillis(): Long = System.currentTimeMillis()
    }
}