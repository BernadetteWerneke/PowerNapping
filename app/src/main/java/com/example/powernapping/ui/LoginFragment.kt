package com.example.powernapping.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.powernapping.MainActivity
import com.example.powernapping.R
import com.example.powernapping.databinding.FragmentLoginBinding
import com.example.powernapping.databinding.FragmentSplashBinding
import kotlinx.coroutines.Dispatchers.Main

class LoginFragment : Fragment() {

    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.loginLoginButton.setOnClickListener {
    //        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_bottomMenuNavigationFragment)
            val intent = Intent (getActivity(), BottomMenuNavigationActivity::class.java)
            getActivity()?.startActivity(intent)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}