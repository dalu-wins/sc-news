package com.daluwi.sc_newshub.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.daluwi.sc_newshub.app.theme.SCNewsHubTheme
import com.daluwi.sc_newshub.presentation.navigation.AppNavigationBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SCNewsHubTheme {
                AppNavigationBar()
            }
        }
    }
}