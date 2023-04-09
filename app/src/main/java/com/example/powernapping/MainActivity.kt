package com.example.powernapping

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find reference to NavHostNavigation with findNavController
        val navController = this.findNavController(R.id.nav_host_fragment)
        // Find reference to bottom navigation view
        val navView: BottomNavigationView = findViewById(R.id.bottom_nav_view11)

        //keine bottom navigation bar auf Login und SignUp Screen
        navController.addOnDestinationChangedListener{_, destination, _ ->
            bottom_nav_view11.visibility =
                if (destination.id == R.id.loginFragment || destination.id == R.id.signUpFragment){
                View.GONE
            } else {
                View.VISIBLE
            }
        }
        // Hook your navigation controller to bottom navigation view
        navView.setupWithNavController(navController)


        // full screen in App
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }*/
    }

}