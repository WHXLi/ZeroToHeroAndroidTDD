package ru.easycode.zerotoheroandroidtdd.utils

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback(
    private val old: List<CharSequence>,
    private val new: List<CharSequence>,
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }
}