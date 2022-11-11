package com.example.countries.ui.language_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countries.adapter.LanguageAdapter
import com.example.countries.databinding.FragmentLanguageListBinding
import com.example.countries.domain.model.DataSource
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * A simple [Fragment] subclass.
 * Use the [LanguageModalSheet.newInstance] factory method to
 * create an instance of this fragment.
 */
class LanguageModalSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentLanguageListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: LanguageAdapter
    private var languageList = DataSource().loadLanguages()

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle? ,
    ): View?
    {
        // Inflate the layout for this fragment
        _binding = FragmentLanguageListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?)
    {
        super.onViewCreated(view , savedInstanceState)
        loadLanguages()
    }

    private fun loadLanguages(){
        adapter = LanguageAdapter {
        }
        adapter.submitList(languageList)
        binding.languageRecyclerview.layoutManager =
            LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
        binding.languageRecyclerview.adapter = adapter
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}