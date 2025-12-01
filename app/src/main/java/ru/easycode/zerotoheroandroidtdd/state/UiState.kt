package ru.easycode.zerotoheroandroidtdd.state

sealed class UiState() {

    data class Base(val text: String): UiState()
    data class Max(val text: String): UiState()
    data class Min(val text: String): UiState()
}