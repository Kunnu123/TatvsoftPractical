package com.example.tatvsoftpractical.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.tatvsoftpractical.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val updateHandler = Handler()

        val runnable = Runnable {
            navigateToHomeScreen() // some action(s)
        }

        updateHandler.postDelayed(runnable, 3000)

    }

    //Navigate to To HomeScreen
    private fun navigateToHomeScreen() {
        val homeIntent = Intent(this, MainActivity::class.java)
        startActivity(homeIntent)
    }
}