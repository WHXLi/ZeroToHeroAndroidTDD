package ru.easycode.zerotoheroandroidtdd

import java.io.Serializable

const val RESTORE_KEY = "RESTORE_KEY"

data class RestoreData(
    val inputtedText: String,
    val inputOnFocus: Boolean,
    val titleText: String,
): Serializable