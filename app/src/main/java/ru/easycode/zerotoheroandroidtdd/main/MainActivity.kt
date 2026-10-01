package ru.easycode.zerotoheroandroidtdd.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.App
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import ru.easycode.zerotoheroandroidtdd.navigation.screen.MainScreen

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigation()
        if (savedInstanceState == null) MainScreen.show(
            supportFragmentManager = supportFragmentManager,
            containerId = R.id.rootLayout,
        )
    }

    private fun initNavigation() {
        (application as App).navigation.liveData().observe(this) { screen ->
            screen.show(
                supportFragmentManager = supportFragmentManager,
                containerId = R.id.rootLayout,
            )
        }
    }
}