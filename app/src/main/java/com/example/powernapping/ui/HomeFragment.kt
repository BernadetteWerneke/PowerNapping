package com.example.powernapping.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.powernapping.R
import com.example.powernapping.ViewModel.MainViewModel
import com.example.powernapping.adapter.CategoryAdapter
import com.example.powernapping.data.Datasource
import com.example.powernapping.databinding.FragmentHomeBinding
/**
 * HomeFragment ist der MainScreen der App
 * sollte kein User eingeloggt sein, wird man automatisch zum Login weitergeleitet
 */

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

        //authentication firebase-----------------------------------
        viewModel.currentUser.observe(viewLifecycleOwner){
            if(it == null){
                findNavController().navigate(R.id.loginFragment)
            }
        }

        binding.homeLogoutImage.setOnClickListener{
            viewModel.logout()                        //User=0, d.h. automatisch zum Login navigiert
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}