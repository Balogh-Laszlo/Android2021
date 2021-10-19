package com.example.quizapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment


class MainActivity : AppCompatActivity() {
    private lateinit var navController:NavController

    companion object {
        const val TAG = "MAIN ACTIVITY"
        const val CONTACT_PERMISSION_CODE = 440
        const val EXTERNAL_PERMISSION_CODE = 442
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeView()
    }

    private fun initializeView() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nhFragment) as NavHostFragment
        navController = navHostFragment.navController
    }

}





