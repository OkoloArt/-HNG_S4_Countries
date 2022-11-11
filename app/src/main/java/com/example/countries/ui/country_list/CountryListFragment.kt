package com.example.countries.ui.country_list

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countries.adapter.CountryAdapter
import com.example.countries.data.remote.dto.country.CountriesDto
import com.example.countries.databinding.FragmentCountryListBinding
import com.example.countries.ui.language_list.LanguageModalSheet
import com.example.countries.ui.viewmodel.CountryListViewModel
import com.example.countries.utils.ConnectivityObserver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class CountryListFragment : Fragment() {

    private var _binding: FragmentCountryListBinding? = null
    private val binding get() = _binding!!

    private val countryListViewModel by viewModels<CountryListViewModel>()
    private lateinit var adapter: CountryAdapter
    private var countriesList = mutableListOf<CountriesDto>()

    @Inject lateinit var connectivityObserver: ConnectivityObserver

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle? ,
    ): View?
    {
        _binding = FragmentCountryListBinding.inflate(inflater , container , false)
        return binding.root

    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?)
    {
        super.onViewCreated(view , savedInstanceState)

        connectivityObserver.observeNetworkStatus().asLiveData().observe(viewLifecycleOwner){
            it?.let {
                if (it.name =="Lost"){
                    binding.countriesRecyclerview.visibility = View.INVISIBLE
           //         binding.loading.visibility = View.VISIBLE
                    Toast.makeText(requireContext(),"No Internet Connection", Toast.LENGTH_SHORT).show()
                }else{
           //         Handler(Looper.getMainLooper()).postDelayed({loadCards() } , 4000)
                    binding.countriesRecyclerview.visibility = View.VISIBLE
                    loadCountries()
                }
            }
        }

        setUpSearchView()
        binding.language.setOnClickListener {
            showLanguageModal()
        }
    }

    private fun setUpSearchView(){
        binding.searchList.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
               adapter.getFilter().filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.getFilter().filter(newText);
                return true
            }

        })
    }

    private fun loadCountries() {
        lifecycleScope.launch {
            countryListViewModel.state.collect { it ->
                it.country.let { countries ->
                    countriesList = countries.toMutableList()
                    adapter = CountryAdapter(countriesList as ArrayList<CountriesDto>) { country ->
                        val action = CountryListFragmentDirections.actionFirstFragmentToSecondFragment(country)
                        findNavController().navigate(action)
                    }
                    binding.countriesRecyclerview.layoutManager =
                        LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
                    binding.countriesRecyclerview.adapter = adapter
                }
            }
        }
    }

    private fun showLanguageModal(){
        val languageModalSheet = LanguageModalSheet()
        languageModalSheet.show(requireFragmentManager() , LanguageModalSheet.TAG)
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}