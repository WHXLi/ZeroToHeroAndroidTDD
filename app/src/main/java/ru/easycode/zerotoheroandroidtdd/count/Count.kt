package ru.easycode.zerotoheroandroidtdd.count

import ru.easycode.zerotoheroandroidtdd.state.UiState

interface Count {

    fun initial(number: String): UiState
    fun increment(number: String): UiState
    fun decrement(number: String): UiState

    class Base(
        val step: Int,
        val max: Int,
        val min: Int,
        var current: Int = 0,
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

        override fun initial(number: String): UiState {
            current = number.toInt()
            return when (current) {
                min -> UiState.Min(number)
                max -> UiState.Max(number)
                else -> UiState.Base(number)
            }
        }

        override fun increment(number: String): UiState {
            val incremented = number.toInt() + step
            current = incremented
            return if (incremented >= max) UiState.Max(max.toString())
            else UiState.Base(incremented.toString())
        }

        override fun decrement(number: String): UiState {
            val decremented = number.toInt() - step
            current = decremented
            return if (decremented <= min) UiState.Min(min.toString())
            else UiState.Base(decremented.toString())
        }
    }
}