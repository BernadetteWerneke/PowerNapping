package com.example.powernapping.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.powernapping.R
import com.example.powernapping.ViewModel.HomeViewModel
import com.example.powernapping.ViewModel.MainViewModel
import com.example.powernapping.adapter.CategoryAdapter
import com.example.powernapping.data.Datasource
import com.example.powernapping.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()    //wird geteilt mit loginFragment + SignUpFragment

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

        // recyclerview of categories with a lot of albums
        val datasource = Datasource()
        val categoryList = datasource.loadCategories()

        binding.categoryRecycler.adapter = CategoryAdapter(categoryList)
       //----------------------------------end recyclerview

        //authentication-----------------------------------
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}