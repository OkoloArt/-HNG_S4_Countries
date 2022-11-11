package com.example.countries.ui.countrydetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.countries.data.remote.dto.country.CountriesDto
import com.example.countries.databinding.FragmentCountryDetailsBinding
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CountryDetailFragment : Fragment()
{

    private var _binding: FragmentCountryDetailsBinding? = null
    private val binding get() = _binding!!

    private val safeArgs: CountryDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle? ,
    ): View?
    {

        _binding = FragmentCountryDetailsBinding.inflate(inflater , container , false)
        return binding.root

    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?)
    {
        super.onViewCreated(view , savedInstanceState)

        val country = safeArgs.country
        bind(countriesDto = country)
    }

    private fun bind(countriesDto: CountriesDto?)
    {
        binding.apply {
            if (countriesDto != null)
            {
                Picasso.get().load(countriesDto.flags.png).into(countryFlagImage)
                population.text = countriesDto.population.toString()
                region.text = countriesDto.region
                capital.text = countriesDto.capital[0]
                motto.text = null
                officialLang.text = null
                ethnic.text = null
                religion.text = null
                government.text = null
                independence.text = null
                area.text = "${countriesDto.area}Km2"
                currency.text = null
                gdp.text = null
                timezone.text = countriesDto.timezones[0]
                dateFormat.text = null
                dialingCode.text = countriesDto.idd.suffixes[0]
                drivingSide.text = countriesDto.car.side
            }
        }
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}