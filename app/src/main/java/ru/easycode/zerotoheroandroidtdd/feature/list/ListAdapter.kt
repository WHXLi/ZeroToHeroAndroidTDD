package ru.easycode.zerotoheroandroidtdd.feature.list

import ru.easycode.zerotoheroandroidtdd.core.view.AbstractAdapter
import ru.easycode.zerotoheroandroidtdd.databinding.ItemElementBinding

class ListAdapter: AbstractAdapter<ItemElementBinding, CharSequence>(
    bindingInflater = ItemElementBinding::inflate
) {

    override fun viewHolder(binding: ItemElementBinding): AbstractHolder = Holder(binding)

    inner class Holder(
        private val binding: ItemElementBinding
    ): AbstractHolder(binding) {

        override fun bind(item: CharSequence) {
            binding.elementTextView.text = item
        }
    }
}