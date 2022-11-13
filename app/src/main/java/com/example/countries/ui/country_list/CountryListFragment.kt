package com.example.countries.ui.country_list

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countries.R
import com.example.countries.adapter.CountriesAdapter
import com.example.countries.data.remote.dto.country.CountriesDto
import com.example.countries.databinding.FragmentCountryListBinding
import com.example.countries.domain.model.CountriesModel
import com.example.countries.ui.filter_list.FilterModalSheet
import com.example.countries.ui.filter_list.FilterViewModel
import com.example.countries.ui.language_list.LanguageModalSheet
import com.example.countries.ui.viewmodel.CountryListViewModel
import com.example.countries.utils.ConnectivityObserver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class CountryListFragment : Fragment() {

    private var _binding: FragmentCountryListBinding? = null
    private val binding get() = _binding!!

    private val countryListViewModel by viewModels<CountryListViewModel>()
    private lateinit var countriesAdapter: CountriesAdapter
    private var countriesList = mutableListOf<CountriesDto>()
    private val filterViewModel : FilterViewModel by activityViewModels()

    private var mSectionList: ArrayList<CountriesModel>? = null

    @Inject lateinit var connectivityObserver: ConnectivityObserver

    private var dayNightMode = true

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

        loadCountry()
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            dayNightMode=!dayNightMode
            binding.apply {
                dayButton.visibility =View.VISIBLE
                nightButton.visibility =View.INVISIBLE
                language.background =ContextCompat.getDrawable(requireContext(),R.drawable.button_white)
                language.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_language_white,0,0,0)
                filter.background = ContextCompat.getDrawable(requireContext(),R.drawable.button_white)
                filter.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_filter_white,0,0,0)
            }
        } else {
            binding.apply {
                dayButton.visibility =View.INVISIBLE
                nightButton.visibility =View.VISIBLE
            }
        }
        connectivityObserver.observeNetworkStatus().asLiveData().observe(viewLifecycleOwner){
            it?.let {
                if (it.name =="Lost"){
                    binding.countriesRecyclerview.visibility = View.INVISIBLE
                    binding.noInternetConnection.visibility = View.VISIBLE
                    Toast.makeText(requireContext(),"No Internet Connection", Toast.LENGTH_SHORT).show()
                }else{
                    binding.countriesRecyclerview.visibility = View.VISIBLE
                    binding.noInternetConnection.visibility = View.INVISIBLE
                    loadCountry()
                    setUpSearchView()
                }
            }
        }
        binding.apply {
            language.setOnClickListener {
                showLanguageModal()
            }
            dayButton.setOnClickListener {
                setDayNightTheme(dayNightMode)
            }
            nightButton.setOnClickListener {
                setDayNightTheme(dayNightMode)
            }
            filter.setOnClickListener{
                showFilterModal()
            }
        }
    }

    private fun showData(){
        lifecycleScope.launch {
            countryListViewModel.state.collect { it ->
                it.country.let { countries ->
                    val countriesModels: ArrayList<CountriesModel> = ArrayList()
                    countriesList = countries.sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it.name.official!! }).toMutableList()
                    for (i in 0 until countriesList.size)
                    {
                        countriesModels.add(CountriesModel(countriesList[i].name.official!!,countriesList[i] , false))
                    }

                    mSectionList = ArrayList()
                    getHeaderListLatter(countriesModels)
                    countriesAdapter = CountriesAdapter(mSectionList!!){ model ->
                        val action = CountryListFragmentDirections.actionFirstFragmentToSecondFragment(model.countriesDto)
                        findNavController().navigate(action)
                    }
                    binding.countriesRecyclerview.layoutManager =
                        LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
                    binding.countriesRecyclerview.adapter = countriesAdapter
                }
            }
        }
    }

    private fun setDayNightTheme(dayNight : Boolean) {
        if (dayNight) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            Toast.makeText(requireContext(),"Night mode on", Toast.LENGTH_SHORT).show()
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            Toast.makeText(requireContext(),"Night mode off", Toast.LENGTH_SHORT).show()
        }
        dayNightMode = !dayNightMode
    }

    private fun setUpSearchView(){
        binding.searchList.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
               countriesAdapter.getFilter().filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                countriesAdapter.getFilter().filter(newText);
                return true
            }

        })
    }

    private fun showLanguageModal(){
        val languageModalSheet = LanguageModalSheet()
        languageModalSheet.show(requireFragmentManager() , LanguageModalSheet.TAG)
    }

    private fun showFilterModal(){
        val filterModalSheet = FilterModalSheet()
        filterModalSheet.show(requireFragmentManager() , FilterModalSheet.TAG)
    }

    private fun loadCountry(){
        filterViewModel.filter.observe(viewLifecycleOwner){ filters ->
            filters?.let {
                if (filters.isEmpty()){
                    showData()
                }else{
                    for (i in 0 until countriesList.size){
                        for (j in filters.indices){
                            if (countriesList[i].continents?.first() == filters[j].childTitle){
                                countriesAdapter.getFilter().filter(filters[j].childTitle)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getHeaderListLatter(countryList: ArrayList<CountriesModel>)
    {
        countryList.sortWith(Comparator { country1 , country2 ->
            country1?.countriesDto?.name?.official?.uppercase(Locale.getDefault())
                ?.compareTo(country2?.countriesDto?.name?.official.toString().uppercase(
                        Locale.getDefault())) ?: 0
        })

        var lastHeader: String? = ""
        val size: Int = countryList.size
        for (i in 0 until size)
        {
            val user = countryList[i]
            val header = user.countriesDto.name.official?.toCharArray()?.first()?.uppercase(Locale.getDefault())
            if (!TextUtils.equals(lastHeader , header))
            {
                lastHeader = header
                mSectionList!!.add(CountriesModel(header!!, user.countriesDto , true))
            }
            mSectionList!!.add(user)
        }
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}