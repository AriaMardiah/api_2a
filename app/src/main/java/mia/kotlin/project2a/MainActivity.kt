package mia.kotlin.project2a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import mia.kotlin.project2a.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding
        by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment)
        binding.bottomMenu.setupWithNavController(navController)
    }
}