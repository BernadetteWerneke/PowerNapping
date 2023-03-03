package com.example.powernapping.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.powernapping.R
import com.example.powernapping.databinding.FragmentBottomMenuNavigationBinding

class BottomMenuNavigationFragment : AppCompatActivity() {

    private var _binding: FragmentBottomMenuNavigationBinding? = null
    private val binding get() = _binding!!

    val Fragment1 = HomeFragment()
    val Fragment2 = LibraryFragment()
    val Fragment3 = TimerFragment()
    val Fragment4 = FavoriteFragment()
    val Fragment5 = DashboardFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_bottom_menu_navigation)

        binding.bottomNavigationView.setOnItemReselectedListener {
            when (it.itemId) {
                /*R.id.home -> replaceFragment(Fragment1)
                R.id.library -> replaceFragment(Fragment2)
                R.id.timer -> replaceFragment(Fragment3)
                R.id.favorite -> replaceFragment(Fragment4)
                R.id.dashboard -> replaceFragment(Fragment5)*/
            }
            true
        }
    }
}

 /*       override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            _binding = FragmentBottomMenuNavigationBinding.inflate(inflater, container, false)
            val view = binding.root

            return view
        }
    }*/

    /*fun replaceFragment(fragment: Fragment) {
    if (fragment != null) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(
            androidx.navigation.fragment.R.id.nav_host_fragment_container, fragment)
        transaction.commit()
    }
}*/