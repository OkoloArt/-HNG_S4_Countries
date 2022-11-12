package com.example.countries.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.databinding.CountryListDetailsBinding
import com.example.countries.databinding.HeaderTitleBinding
import com.example.countries.domain.model.CountriesModel
import com.squareup.picasso.Picasso
import java.util.*


class CountriesAdapter(private val dataSet: ArrayList<CountriesModel>, private val onItemClicked: (CountriesModel) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>()
{

    val initialCountryList = ArrayList<CountriesModel>().apply {
        addAll(dataSet)
    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): RecyclerView.ViewHolder
    {
        return if (viewType == SECTION_VIEW)
        {
            SectionHeaderViewHolder(HeaderTitleBinding.inflate(
                    LayoutInflater.from(parent.context)))
        }
        else ItemViewHolder(CountryListDetailsBinding.inflate(
                LayoutInflater.from(parent.context)))
    }

    override fun getItemViewType(position: Int): Int {
        return if (dataSet[position].isSection) {
            SECTION_VIEW
        }else {
            CONTENT_VIEW
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder , position: Int)
    {
        val current = dataSet[position]
        if (SECTION_VIEW == getItemViewType(position))
        {
            val sectionHeaderViewHolder = holder as SectionHeaderViewHolder
            sectionHeaderViewHolder.bind(current)
            return
        }
        val itemViewHolder = holder as ItemViewHolder
        itemViewHolder.bind(current)
        itemViewHolder.itemView.setOnClickListener {
            onItemClicked(current)
        }
    }

    override fun getItemCount(): Int = dataSet.size

    class ItemViewHolder(private val binding: CountryListDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(countriesModel: CountriesModel) {
            Picasso.get().load(countriesModel.countriesDto.flags.png).into(binding.countryImage)
            binding.countryName.text = countriesModel.countriesDto.name.official ?: ""
            binding.countryCapital.text = countriesModel.countriesDto.capital?.first() ?: ""
        }
    }

    inner class SectionHeaderViewHolder(private val binding: HeaderTitleBinding) :
        RecyclerView.ViewHolder(binding.root)
    {
        fun bind(countriesModel: CountriesModel){
            binding.headerTitleTextview.text = countriesModel.name
        }
    }

    fun getFilter(): Filter
    {
        return cityFilter
    }

    private val cityFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults
        {
            val filteredList: ArrayList<CountriesModel> = ArrayList()
            if (constraint == null || constraint.isEmpty()) {
                initialCountryList.let { filteredList.addAll(it) }
            } else {
                val query = constraint.toString().trim().lowercase()
                initialCountryList.forEach {
                    if (it.countriesDto.name.official?.lowercase(Locale.ROOT)?.contains(query) == true) {
                        filteredList.add(it)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence? , results: FilterResults?) {
            if (results?.values is ArrayList<*>)
            {
                dataSet.clear()
                dataSet.addAll(results.values as ArrayList<CountriesModel>)
                notifyDataSetChanged()
            }
        }
    }

    companion object
    {
        const val SECTION_VIEW = 0
        const val CONTENT_VIEW = 1
    }
}