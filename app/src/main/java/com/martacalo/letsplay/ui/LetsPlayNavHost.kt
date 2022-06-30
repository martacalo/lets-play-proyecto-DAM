package com.martacalo.letsplay.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.martacalo.letsplay.ui.library.LibraryRoute
import com.martacalo.letsplay.ui.library.contract.LibraryNavigationEvent
import com.martacalo.letsplay.ui.search.SearchRoute
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun LetsPlayNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "library"
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {

        composable(route = "library") {
            LibraryRoute(viewModel = hiltViewModel()) {
                when(it) {
                    LibraryNavigationEvent.NavigateToSearch ->
                        navController.navigate("search")
                    is LibraryNavigationEvent.NavigateToGame ->
                        navController.navigate("game/$it")
                }
            }
        }

        composable(route = "search") {
            SearchRoute(viewModel = hiltViewModel()) {
                navController.popBackStack()
            }
        }

    }

}
