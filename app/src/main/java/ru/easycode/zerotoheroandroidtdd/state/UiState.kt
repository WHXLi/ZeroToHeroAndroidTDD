package ru.easycode.zerotoheroandroidtdd.state

sealed class UiState(val text: String) {

    class Base(text: String): UiState(text)

    class Max(text: String): UiState(text)

    override fun equals(other: Any?): Boolean {
        if (other is UiState) return text == other.text
        return false
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}