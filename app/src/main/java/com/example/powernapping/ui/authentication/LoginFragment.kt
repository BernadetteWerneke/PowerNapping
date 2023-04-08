package com.example.powernapping.ui.authentication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.powernapping.ViewModel.MainViewModel
import com.example.powernapping.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()    //wird geteilt mit HomeFragment + SignUpFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //login button gedrückt
        binding.loginSigninButton.setOnClickListener {
            val email = binding.loginEmailAddressText.text.toString()
            val password = binding.loginPasswordText.text.toString()


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}