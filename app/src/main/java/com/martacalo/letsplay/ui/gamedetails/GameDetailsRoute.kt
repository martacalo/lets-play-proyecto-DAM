@file:OptIn(ExperimentalMaterial3Api::class)

package com.martacalo.letsplay.ui.gamedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.martacalo.letsplay.ui.gamedetails.contract.GameDetailsNavigationEvent
import com.martacalo.letsplay.ui.gamedetails.contract.GameDetailsViewState

@Composable
fun GameDetailsRoute(
    viewModel: GameDetailsViewModel = viewModel(),
    gameId: String,
    onNavigate: (GameDetailsNavigationEvent) -> Unit,
) {

    val state by viewModel.state.collectAsState()

    viewModel.getGame(gameId)

    GameDetailsView(state = state) { onNavigate(it) }
}

@Composable
fun GameDetailsView(
    state: GameDetailsViewState,
    onNavigate: (GameDetailsNavigationEvent) -> Unit,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                title = { },
                navigationIcon = {
                    IconButton(onClick = { onNavigate(GameDetailsNavigationEvent.NavigateBack) }) {
                        Icon(Icons.Rounded.ArrowBack, contentDescription = "")
                    }
                },
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(
                top = paddingValues.calculateTopPadding(),
                start = 16.dp,
                end = 16.dp
            )
        ) {
            Text(text = state.gameDetails.name)
        }
    }

}
