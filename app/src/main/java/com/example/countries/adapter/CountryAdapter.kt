package com.example.countries.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.data.remote.dto.country.CountriesDto
import com.example.countries.databinding.CountryListDetailsBinding
import com.squareup.picasso.Picasso
import java.util.*

class CountryAdapter(
    private val dataSet: ArrayList<CountriesDto> ,
    private val onItemClicked: (CountriesDto) -> Unit ,
) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>()
{

    val initialCountryList = ArrayList<CountriesDto>().apply {
        dataSet.let { addAll(it) }
    }

    class CountryViewHolder(private val binding: CountryListDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(countriesDto: CountriesDto) {
            Picasso.get().load(countriesDto.flags.png).into(binding.countryImage)
            binding.countryName.text = countriesDto.name.official
            binding.countryCapital.text = countriesDto.capital[0]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): CountryViewHolder {
        return CountryViewHolder(CountryListDetailsBinding.inflate(
                        LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder , position: Int) {
        val current = dataSet[position]
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)
    }

    override fun getItemCount(): Int = dataSet.size

    fun getFilter(): Filter {
        return cityFilter
    }

    private val cityFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults
        {
            val filteredList: ArrayList<CountriesDto> = ArrayList()
            if (constraint == null || constraint.isEmpty()) {
                initialCountryList.let { filteredList.addAll(it) }
            } else {
                val query = constraint.toString().trim().lowercase()
                initialCountryList.forEach {
                    if (it.name.official.lowercase(Locale.ROOT).contains(query)) {
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
                dataSet.addAll(results.values as ArrayList<CountriesDto>)
                notifyDataSetChanged()
            }
        }
    }
}