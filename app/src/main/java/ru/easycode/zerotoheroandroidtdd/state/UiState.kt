package ru.easycode.zerotoheroandroidtdd.state

interface UiState {

    class Base(text: String): UiState

    class Max(text: String): UiState
}