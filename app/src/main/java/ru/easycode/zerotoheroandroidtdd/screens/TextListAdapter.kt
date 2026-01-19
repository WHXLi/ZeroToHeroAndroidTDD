package ru.easycode.zerotoheroandroidtdd.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemTextListBinding

class TextListAdapter(
    private val data: MutableList<CharSequence> = mutableListOf()
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

    override fun getItemCount(): Int = data.size

    fun addItems(items: List<CharSequence>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    inner class TextListHolder(
        private val binding: ItemTextListBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.elementTextView.text = data[position]
        }
    }
}