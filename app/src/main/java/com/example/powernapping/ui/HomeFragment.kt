package com.example.powernapping.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.powernapping.HomeViewModel
import com.example.powernapping.R
import com.example.powernapping.adapter.CategoryAdapter
import com.example.powernapping.data.Datasource
import com.example.powernapping.databinding.ActivityMainBinding
import com.example.powernapping.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_bottom_menu_navigation.*

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val datasource = Datasource()
        val categoryList = datasource.loadCategories()

        binding.categoryRecycler.adapter = CategoryAdapter(categoryList)

        //viewModel.category.obser
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}