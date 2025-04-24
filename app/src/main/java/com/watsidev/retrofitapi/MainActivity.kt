package com.watsidev.retrofitapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.watsidev.retrofitapi.navigation.navigationWrapper
import com.watsidev.retrofitapi.ui.theme.RetrofitApiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetrofitApiTheme {
                navigationWrapper()
            }
        }
    }
}