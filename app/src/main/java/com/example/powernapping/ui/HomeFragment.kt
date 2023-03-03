package com.example.powernapping.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.powernapping.R
import com.example.powernapping.adapter.CategoryAdapter
import com.example.powernapping.data.Datasource
import com.example.powernapping.databinding.ActivityMainBinding
import com.example.powernapping.databinding.FragmentHomeBinding

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        val datasource = Datasource()
        val categoryList = datasource.loadCategories()

        binding.categoryRecycler.adapter = CategoryAdapter(categoryList)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}