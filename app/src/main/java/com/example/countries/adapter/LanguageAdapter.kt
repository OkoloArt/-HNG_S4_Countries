package com.example.countries.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.databinding.LanguageListItemBinding
import com.example.countries.domain.model.LanguageModel


class LanguageAdapter(private val onItemClicked: (LanguageModel) -> Unit) :
    ListAdapter<LanguageModel , LanguageAdapter.LanguageViewHolder>(DiffCallback)
{

    private var lastCheckedPosition = -1

    class LanguageViewHolder(private val binding: LanguageListItemBinding) :
        RecyclerView.ViewHolder(binding.root)
    {
        var radioButton: RadioButton = binding.languageChecked
        fun bind(languageModel: LanguageModel)
        {
            binding.languageName.text = languageModel.languageName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): LanguageViewHolder
    {
        return LanguageViewHolder(
                LanguageListItemBinding.inflate(
                        LayoutInflater.from(
                                parent.context
                        )
                )
        )
    }

    override fun onBindViewHolder(holder: LanguageViewHolder , position: Int)
    {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            holder.radioButton.isChecked = position == lastCheckedPosition
        }
        holder.bind(current)
    }
    companion object
    {
        private val DiffCallback = object : DiffUtil.ItemCallback<LanguageModel>()
        {
            override fun areItemsTheSame(oldItem: LanguageModel , newItem: LanguageModel): Boolean
            {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: LanguageModel ,
                newItem: LanguageModel ,
            ): Boolean
            {
                return oldItem.languageName == newItem.languageName
            }
        }
    }
}
