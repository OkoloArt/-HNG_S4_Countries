package com.example.countries.ui.filter_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countries.adapter.FilterAdapter
import com.example.countries.databinding.FragmentFilterModalSheetBinding
import com.example.countries.domain.model.FilterChildData
import com.example.countries.domain.model.FilterParentModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * A simple [Fragment] subclass.
 * Use the [FilterModalSheet.newInstance] factory method to
 * create an instance of this fragment.
 */
class FilterModalSheet : BottomSheetDialogFragment()
{

    private var _binding: FragmentFilterModalSheetBinding? = null
    private val binding get() = _binding!!

    private val listData: MutableList<FilterParentModel> = ArrayList()
    private val filterChoice: MutableList<FilterChildData> = ArrayList()
    private val filterViewModel : FilterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle? ,
    ): View?
    {
        // Inflate the layout for this fragment
        _binding = FragmentFilterModalSheetBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?)
    {
        super.onViewCreated(view , savedInstanceState)
        showFilterOption()
        binding.dismiss.setOnClickListener {
            dismiss()
        }
    }


    private fun showFilterOption()
    {
        val parentData: Array<String> = arrayOf("Continents" , "Time Zones")
        val childDataData1: MutableList<FilterChildData> =
            mutableListOf(FilterChildData("Africa"),
                          FilterChildData("Antarctica"),
                          FilterChildData("Asia"),
                          FilterChildData("Australia"),
                          FilterChildData("Europe") ,
                          FilterChildData("North America"),
                          FilterChildData("South America"))

        val childDataData2: MutableList<FilterChildData> =
            mutableListOf(FilterChildData("Atlantic Standard Time") ,
                          FilterChildData("Eastern Standard Time") ,
                          FilterChildData("Central Standard Time"),
                          FilterChildData("Mountain Standard Time"),
                          FilterChildData("Pacific Standard Time"),
                          FilterChildData("Hawaii-Aleutian Standard Time"),
                          FilterChildData("Samoa Standard Time"),
                          FilterChildData("Chamorro  Standard Time"))

        val parentObj1 = FilterParentModel(parentTitle = parentData[0] , subList = childDataData1)
        val parentObj2 = FilterParentModel(parentTitle = parentData[1] , subList = childDataData2)

        listData.add(parentObj1)
        listData.add(parentObj2)

        binding.exRecycle.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.exRecycle.adapter = FilterAdapter(listData){
            filterParentModel ->
            filterChoice.clear()
            filterChoice.addAll(filterParentModel.subList)
        }
        binding.results.setOnClickListener {
            filterViewModel.filterWords(filterChoice)
            dismiss()
        }
        binding.reset.setOnClickListener {
            filterViewModel.filterWords(emptyList())
            dismiss()
        }
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}