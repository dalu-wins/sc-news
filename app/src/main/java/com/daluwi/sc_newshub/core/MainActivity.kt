package com.daluwi.sc_newshub.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.daluwi.sc_newshub.core.navigation.AppNavigation
import com.daluwi.sc_newshub.core.theme.SCNewsHubTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SCNewsHubTheme {
                AppNavigation()
            }
        }
    }
}