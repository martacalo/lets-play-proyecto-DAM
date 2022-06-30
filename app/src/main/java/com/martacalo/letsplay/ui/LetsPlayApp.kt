package com.martacalo.letsplay.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.martacalo.letsplay.ui.theme.LetsPlayTheme

@Composable
fun LetsPlayApp() {
    LetsPlayTheme {
        val navController = rememberNavController()

        LetsPlayNavHost(navController = navController)
    }
}
