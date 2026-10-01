package ru.easycode.zerotoheroandroidtdd.core.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class AbstractAdapter<VB: ViewBinding, E: Any>(
    private val bindingInflater: (
        inflater: LayoutInflater,
        parent: ViewGroup?,
        attachToParent: Boolean,
    ) -> VB,
    private val items: MutableList<E> = mutableListOf(),
): RecyclerView.Adapter<AbstractAdapter<VB, E>.AbstractHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: AbstractAdapter<VB, E>.AbstractHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AbstractAdapter<VB, E>.AbstractHolder {
        val binding = bindingInflater(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return viewHolder(binding)
    }

    fun update(newItems: List<E>) {
        val diffUtilCallback = DiffUtilCallback(items, newItems)
        val diff = DiffUtil.calculateDiff(diffUtilCallback)
        items.clear()
        items.addAll(newItems)
        diff.dispatchUpdatesTo(this)
    }

    abstract fun viewHolder(binding: VB): AbstractHolder

    abstract inner class AbstractHolder(
        binding: VB,
    ): RecyclerView.ViewHolder(binding.root) {

        abstract fun bind(item: E)
    }

    class DiffUtilCallback<E: Any>(
        private val old: List<E>,
        private val new: List<E>,
    ): DiffUtil.Callback() {

        override fun getOldListSize(): Int = old.size

        override fun getNewListSize(): Int = new.size

        override fun areItemsTheSame(
            oldItemPosition: Int,
            newItemPosition: Int,
        ): Boolean = old[oldItemPosition] == new[newItemPosition]

        override fun areContentsTheSame(
            oldItemPosition: Int,
            newItemPosition: Int,
        ): Boolean = old[oldItemPosition] == new[newItemPosition]
    }
}