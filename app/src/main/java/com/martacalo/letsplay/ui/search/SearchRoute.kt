package com.martacalo.letsplay.ui.search

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.martacalo.letsplay.ui.common.SearchBar
import com.martacalo.letsplay.ui.search.model.Game
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalCoroutinesApi
@Composable
fun SearchRoute(
    viewModel: SearchViewModel = viewModel(),
    onBackButton: () -> Unit,
) {
    viewModel.search()

    val viewState by viewModel.state.collectAsState()
    val context = LocalContext.current
    
    LaunchedEffect(viewModel) {
        viewModel
            .effect
            .filterIsInstance<SearchViewEffect>()
            .onEach {
                when (it) {
                    is SearchViewEffect.Toast ->
                        Toast.makeText(context, "${it.message} added to your library", Toast.LENGTH_SHORT).show()
                }
            }
            .collect()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                title = { },
                navigationIcon = {
                    IconButton(onClick = { onBackButton() }) {
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

            Spacer(modifier = Modifier.size(16.dp))

            SearchBar(modifier = Modifier.fillMaxWidth()) { viewModel.search(it) }

            Spacer(modifier = Modifier.size(16.dp))

            GamesList(games = viewState.gamesList) { viewModel.selectGame(it) }

        }
    }
}

@Composable
fun GamesList(
    games: List<Game> = listOf(),
    onClick: (Game) -> Unit,
) {
    LazyColumn {
        items(games) { game ->
            GameItem(game = game) { onClick(game) }
        }
    }
}

@Composable
fun GameItem(
    game: Game,
    onClick: (Game) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .size(56.dp)
            .clickable(
                onClick = { onClick(game) },
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(180.dp)
        ) {
            AsyncImage(
                model = game.images.thumb,
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
            )
            Text(
                text = game.name,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(Icons.Rounded.Person, contentDescription = "Number of players")
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = game.players,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
            )

            Spacer(modifier = Modifier.size(32.dp))

            Icon(Icons.Rounded.Refresh, contentDescription = "Play time")
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = game.playtime,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
            )
        }
    }
}
