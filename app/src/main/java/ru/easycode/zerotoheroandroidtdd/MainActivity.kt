package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import ru.easycode.zerotoheroandroidtdd.databinding.RvUnitBinding

private const val RV_DATA_KEY = "RV_DATA_KEY"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val textListAdapter = TextListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        initListeners()
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = textListAdapter
        }
    }

    private fun initListeners() {
        binding.inputEditText.addTextChangedListener {
            binding.actionButton.isEnabled = it?.isNotEmpty() == true
        }
        binding.actionButton.setOnClickListener {
            val data = listOf(binding.inputEditText.text.toString())
            textListAdapter.data.addAll(data)
            textListAdapter.notifyDataSetChanged()
            binding.inputEditText.text?.clear()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getStringArray(RV_DATA_KEY)?.let {
            textListAdapter.data.addAll(it)
            textListAdapter.notifyDataSetChanged()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArray(RV_DATA_KEY, textListAdapter.data.toTypedArray())
    }

    private inner class TextListAdapter(
        val data: MutableList<String> = mutableListOf()
    ): RecyclerView.Adapter<TextListAdapter.ViewHolder>() {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ) = ViewHolder(RvUnitBinding.inflate(layoutInflater))

        override fun onBindViewHolder(
            holder: ViewHolder,
            position: Int
        ) {
            holder.binding.elementTextView.text = data[position]
        }

        override fun getItemCount(): Int = data.size

        private inner class ViewHolder(
            val binding: RvUnitBinding
        ): RecyclerView.ViewHolder(binding.root)
    }
}