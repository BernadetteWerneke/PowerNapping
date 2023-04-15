package com.example.powernapping.ui.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.powernapping.R
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

        viewModel.toast.observe(viewLifecycleOwner){
            if (it != null){
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        //falls User eingeloggt, dann gleich zum Home
        viewModel.currentUser.observe(viewLifecycleOwner){
            if (it != null){
                findNavController().navigate(R.id.homeFragment3)
            }
        }

        //login button gedrückt
        binding.loginSigninButton.setOnClickListener {
            val email = binding.loginEmailAddressText.text.toString()
            val password = binding.loginPasswordText.text.toString()

            if (!email.isNullOrEmpty() && !password.isNullOrEmpty()){
                viewModel.login(email, password)
            }
        }

        //signUp button gedrückt
        binding.loginSignupButton.setOnClickListener{
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}