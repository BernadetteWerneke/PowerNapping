package com.example.powernapping.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.powernapping.R
import kotlinx.android.synthetic.main.fragment_bottom_menu_navigation.*


class BottomMenuNavigationActivity : AppCompatActivity() {

    // 4 bottom menu for frame container
    val homeFrag = HomeFragment()
    val timerFrag = TimerFragment()
    val favoriteFrag = FavoriteFragment()
    val dashboardFrag = DashboardFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_bottom_menu_navigation)
        replaceFragment(homeFrag)

        bottom_navigation.setOnItemSelectedListener {
        //bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> replaceFragment(homeFrag)
                R.id.ic_timer -> replaceFragment(timerFrag)
                R.id.ic_favorite -> replaceFragment(favoriteFrag)
                R.id.ic_dashboard -> replaceFragment(dashboardFrag)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
            }
        }
}