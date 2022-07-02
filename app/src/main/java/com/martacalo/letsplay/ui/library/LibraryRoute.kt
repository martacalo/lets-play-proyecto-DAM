package com.martacalo.letsplay.ui.library

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.martacalo.letsplay.R
import com.martacalo.letsplay.ui.library.contract.LibraryNavigationEvent
import com.martacalo.letsplay.ui.library.model.LibraryGame
import kotlinx.coroutines.FlowPreview


@FlowPreview
@Composable
fun LibraryRoute(
    modifier: Modifier = Modifier,
    viewModel: LibraryViewModel = viewModel(),
    navigateTo: (LibraryNavigationEvent) -> Unit,
) {

    val state by viewModel.state.collectAsState()

    viewModel.loadLibrary()

    Library(state = state) { navigateTo(it) }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Library(
    modifier: Modifier = Modifier,
    state: LibraryViewState,
    onNavigate: (LibraryNavigationEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNavigate(LibraryNavigationEvent.NavigateToSearch) },
            ) {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding() + 16.dp)
        ){
            items(items = state.library.games, itemContent = {
                LibraryItem(game = it, onClick = { onNavigate(LibraryNavigationEvent.NavigateToGame(it)) })
                Spacer(modifier = Modifier.size(4.dp))
            })
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryItem(
    modifier: Modifier = Modifier,
    game: LibraryGame,
    onClick: (String) -> Unit,
) {
    Surface(
        onClick = { onClick(game.id) },
        shadowElevation = 2.dp,
        tonalElevation = 2.dp,
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
        ) {
            AsyncImage(
                model = game.imagesEntity.medium,
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
                        text = game.name,
                        style = MaterialTheme.typography.headlineSmall,
                    )

                    Text(text = "#${game.rank}")
                }
                game.descriptionPreview?.let {
                    Text(
                        text = it,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewLibrary() {
}
