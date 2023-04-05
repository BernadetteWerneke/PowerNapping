package com.example.powernapping.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.powernapping.R
import com.example.powernapping.ViewModel.ApiStatus
import com.example.powernapping.ViewModel.FavoriteViewModel
import com.example.powernapping.adapter.FavoritenAdapter
import com.example.powernapping.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private var _binding : FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel : FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gotAdapter = FavoritenAdapter()
        binding.FavoritenRecyclerView.adapter = gotAdapter

        //observe alben
        viewModel.alben.observe(viewLifecycleOwner){
            gotAdapter.submitList(it)       //data are loaded into recycler
        }

        //observe internet connection with error handling
        viewModel.loading.observe(viewLifecycleOwner){
            when (it) {
                //show progress bar while loading
                ApiStatus.LOADING -> {
                    Log.e("Achtung", "Achtung1")
                    binding.favoriteLoadingError.visibility = View.GONE
                    binding.favoriteProgressBar.visibility = View.VISIBLE
                }
                ApiStatus.DONE -> {
                    Log.e("Achtung", "Achtung2")
                    binding.favoriteLoadingError.visibility = View.GONE
                    binding.favoriteProgressBar.visibility = View.GONE
                }
                //show cloud image while no internet connection    //TODO diesen Part verschieben in FavoriteViewModel
                ApiStatus.ERROR -> {
                    Log.e("Achtung", "Achtung3")
                    binding.favoriteLoadingError.visibility = View.VISIBLE
                    binding.favoriteProgressBar.visibility = View.GONE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}