package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.newsapp.presentation.onstart.components.OnStartPage
import com.example.newsapp.presentation.onstart.onstartScreen
import com.example.newsapp.ui.theme.NewsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            NewsAppTheme {
                // A surface container using the 'background' color from the theme
                onstartScreen { navigateToHomeActivity() }

            }
        }
    }
        private fun navigateToHomeActivity() {
            val intent = Intent(this, HomeScreenActivity::class.java)
            startActivity(intent)
            finish() // Optional: Close the current activity if needed
        }

}

