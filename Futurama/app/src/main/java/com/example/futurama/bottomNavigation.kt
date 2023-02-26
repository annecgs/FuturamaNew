package com.example.futurama

import android.os.Bundle
import android.os.Handler
import android.view.Window
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.futurama.databinding.ActivityBottomNavigationBinding
import com.example.futurama.ui.dashboard.DashboardFragment
import com.example.futurama.ui.home.HomeFragment
import com.example.futurama.ui.notifications.NotificationsFragment


class bottomNavigation : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_bottom_navigation)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        Handler().postDelayed({
            badgeSetup(R.id.navigation_dashboard,2000)
        },2000)

        cleanQtdBadge()

    }

    private fun cleanQtdBadge(){
        binding.navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    setCurrentFragment(HomeFragment())
                }
                R.id.navigation_dashboard -> {
                    setCurrentFragment(DashboardFragment())
                    badgeClear(R.id.navigation_dashboard)
                }
                R.id.navigation_notifications -> {
                    setCurrentFragment(NotificationsFragment())
                }
            }
            true
        }
    }

    private fun badgeSetup(id: Int, alerts: Int) {
        val badge = binding.navView.getOrCreateBadge(id)
        badge.isVisible = true
        badge.number = alerts
    }

    private fun badgeClear(id: Int) {
        val badgeDrawable = binding.navView.getBadge(id)
        if (badgeDrawable != null) {
            badgeDrawable.isVisible = false
            badgeDrawable.clearNumber()
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment_activity_bottom_navigation, fragment)
            commit()
        }

}