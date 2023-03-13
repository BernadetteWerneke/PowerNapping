package com.example.powernapping.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.powernapping.R
import com.example.powernapping.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    //binding SplashFragment
    private var _binding : FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val view = binding.root

        //only testing to go to next screen TODO delete later, omly testing!!!
        binding.splashPowerText.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_loginFragment)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}