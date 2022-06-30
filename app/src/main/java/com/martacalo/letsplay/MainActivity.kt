package com.martacalo.letsplay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.martacalo.letsplay.ui.LetsPlayApp
import com.martacalo.letsplay.ui.search.SearchRoute
import com.martacalo.letsplay.ui.theme.LetsPlayTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LetsPlayTheme {
                LetsPlayApp()
            }
        }
    }
}
