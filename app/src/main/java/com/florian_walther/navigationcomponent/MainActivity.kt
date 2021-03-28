package com.florian_walther.navigationcomponent

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.florian_walther.navigationcomponent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // calling findNavController() will cause a crash. must code like below instead
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        // remove the Up button when we switch between bottom nav tabs
        val destinations = setOf(R.id.homeFragment, R.id.searchFragment)
        appBarConfiguration = AppBarConfiguration(destinations)

        setSupportActionBar(binding.toolbar)
        // connect the toolbar to the navController
        setupActionBarWithNavController(navController, appBarConfiguration)

        // set up bottom nav
        binding.bottomNav.setupWithNavController(navController)
    }

    // for the Up button
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_terms_n_conditions -> {
                val action = NavGraphDirections.actionGlobalTermsFragment()
                navController.navigate(action)
                true
            }
            else -> {
                // navigate to SettingsFragment; must ensure menu item ID matches SettingsFragment ID in nav_graph.xml
                item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
            }
        }
    }
}