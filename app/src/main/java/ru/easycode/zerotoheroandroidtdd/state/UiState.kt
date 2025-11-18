package ru.easycode.zerotoheroandroidtdd.state

sealed class UiState() {

    data class Base(private val text: String): UiState()
    data class Max(private val text: String): UiState()
    data class Min(private val text: String): UiState()
}