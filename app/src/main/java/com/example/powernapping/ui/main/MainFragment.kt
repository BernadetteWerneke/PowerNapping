package com.example.powernapping.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.powernapping.R
import com.example.powernapping.ViewModel.MainViewModel
import com.example.powernapping.databinding.FragmentLoginBinding


/**
 * MainFragment ist der MainScreen der App
 * sollte kein User eingeloggt sein, wird man automatisch zum Login weitergeleitet
 */

class MainFragment: Fragment() {
    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* viewModel.currentUser.observe(viewLifecycleOwner){
            if (it == null){
                findNavController().navigate(R.id.loginFragment)
            }
        }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}