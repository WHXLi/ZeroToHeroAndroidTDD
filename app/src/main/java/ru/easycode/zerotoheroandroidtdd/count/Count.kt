package ru.easycode.zerotoheroandroidtdd.count

import ru.easycode.zerotoheroandroidtdd.state.UiState

interface Count {

    fun initial(number: String): UiState
    fun increment(number: String): UiState
    fun decrement(number: String): UiState

    class Base(
        private val step: Int,
        private val max: Int,
        private val min: Int,
    ): Count {

        init {
            if (step < 1 ) throw IllegalStateException(
                "step should be positive, but was $step"
            )
            if (max < 1) throw IllegalStateException(
                "max should be positive, but was $max"
            )
            if (max < step) throw IllegalStateException(
                "max should be more than step"
            )
        }

        override fun initial(number: String): UiState = when {
            number == min -> UiState.Min
        }

        override fun increment(number: String): UiState {
            val incremented = number.toInt() + step
            return if (incremented >= max) UiState.Max(max.toString())
            else UiState.Base(incremented.toString())
        }

        override fun decrement(number: String): UiState {
            val decremented = number.toInt() - step
            return if (decremented <= min) UiState.Max(min.toString())
            else UiState.Base(decremented.toString())
        }
    }
}