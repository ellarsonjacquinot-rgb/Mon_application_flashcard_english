package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.viewmodel.MainViewModel
import com.example.ui.navigation.AppNavigation
import com.example.ui.theme.MalaGasyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MalaGasyTheme {
                val mainViewModel: MainViewModel = viewModel()
                AppNavigation(viewModel = mainViewModel)
            }
        }
    }
}
