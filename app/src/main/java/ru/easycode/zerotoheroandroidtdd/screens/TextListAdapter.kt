package ru.easycode.zerotoheroandroidtdd.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemTextListBinding
import ru.easycode.zerotoheroandroidtdd.utils.DiffUtilCallback

class TextListAdapter(
    private val items: MutableList<CharSequence> = mutableListOf()
): RecyclerView.Adapter<TextListAdapter.TextListHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = TextListHolder(
        binding = ItemTextListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: TextListHolder,
        position: Int
    ) {
      holder.bind(position)
    }

    override fun getItemCount(): Int = items.size

    fun update(newItems: List<CharSequence>) {
        val diffUtilCallback = DiffUtilCallback(items, newItems)
        val diff = DiffUtil.calculateDiff(diffUtilCallback)
        items.clear()
        items.addAll(newItems)
        diff.dispatchUpdatesTo(this)
    }

    inner class TextListHolder(
        private val binding: ItemTextListBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.elementTextView.text = items[position]
        }
    }
}