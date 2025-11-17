package ru.easycode.zerotoheroandroidtdd.count

import ru.easycode.zerotoheroandroidtdd.state.UiState

interface Count {

    fun increment(number: String): UiState

    class Base(private val step: Int): Count {

        init {
            if (step < 1 ) throw IllegalStateException(
                "step should be positive, but was $step"
            )
        }

        override fun increment(number: String) = UiState()
    }
}