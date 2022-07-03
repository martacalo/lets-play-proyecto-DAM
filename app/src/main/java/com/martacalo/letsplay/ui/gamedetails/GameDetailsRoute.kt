@file:OptIn(ExperimentalMaterial3Api::class)

package com.martacalo.letsplay.ui.gamedetails

import androidx.compose.foundation.layout.*
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ChildCare
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.martacalo.letsplay.ui.gamedetails.contract.GameDetailsNavigationEvent
import com.martacalo.letsplay.ui.gamedetails.contract.GameDetailsViewState
import com.martacalo.letsplay.ui.gamedetails.model.GameDetails

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
            GameDetailCard(gamedetail = state.gameDetails)

        }
    }

}
@Composable
fun GameDetailCard(gamedetail: GameDetails) {

    //Add padding around our message
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            AsyncImage(
                model = gamedetail.imagesEntity.medium,
                contentDescription = "Game cover",
                modifier = Modifier
                    .size(100.dp)
            )

            Spacer(modifier = Modifier.size(16.dp))

            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = gamedetail.name,
                        style = MaterialTheme.typography.headlineSmall,
                    )

                    Text(text = "#${gamedetail.rank}")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    GameDetail(icon = Icons.Rounded.Person, text = "${gamedetail.minPlayers} - ${gamedetail.maxPlayers}")

                    Spacer(modifier = Modifier.size(16.dp))

                    GameDetail(icon = Icons.Rounded.Schedule, text = gamedetail.playtime)

                    Spacer(modifier = Modifier.size(16.dp))

                    GameDetail(icon = Icons.Rounded.ChildCare, text = "+${gamedetail.minAge}")
                }
                gamedetail.descriptionPreview?.let {
                    Text(
                        text = it,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}

@Composable
fun GameDetail(
    icon: ImageVector,
    text: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        androidx.compose.material.Icon(
            icon,
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
        )
    }

}
