package com.example.powernapping.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.powernapping.R
import com.example.powernapping.ViewModel.HomeViewModel
import com.example.powernapping.ViewModel.MainViewModel
import com.example.powernapping.databinding.FragmentSignupBinding


class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignupBinding
    private val viewModel: MainViewModel by activityViewModels()    //wird geteilt mit loginFragment + HomeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //falls User registriert, dann gleich zum Home
        viewModel.currentUser.observe(viewLifecycleOwner){
            if (it != null){
                findNavController().navigate(R.id.homeFragment)
            }
        }

        //Fehlermeldungen ausgeben
        viewModel.toast.observe(viewLifecycleOwner){
            if (it != null){
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        //Registrierbutton gedrückt
        binding.signupSignupButton.setOnClickListener{
            val mail = binding.signupEmailAddressText.text.toString()
            val password = binding.signupPasswordText.text.toString()

            if (!mail.isNullOrEmpty() && !password.isNullOrEmpty()){
                viewModel.signUp(mail, password)
            }
        }

        //cancel button gedrückt, dann zurück zum login
        binding.signupCancelButton.setOnClickListener{
            findNavController().navigateUp()
        }


    }
}
