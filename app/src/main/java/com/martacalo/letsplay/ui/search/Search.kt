package com.martacalo.letsplay.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.martacalo.letsplay.ui.common.SearchBar
import com.martacalo.letsplay.ui.search.model.Game
import com.martacalo.letsplay.ui.search.model.Images
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun Search(
    viewModel: SearchViewModel = viewModel()
) {
    val viewState by viewModel.state.collectAsState()

    viewModel.init()

    SearchCoin(state = viewState.gamesListState)
}

@Composable
fun SearchCoin(
    state: GamesListState = GamesListState.Loading,
) {
    Column(modifier = Modifier.padding(16.dp)) {

        Spacer(modifier = Modifier.size(16.dp))

        SearchBar(modifier = Modifier.fillMaxWidth()) { }

        Spacer(modifier = Modifier.size(16.dp))

        when (state) {
            is GamesListState.Success -> GamesList(games = state.gamesList, isLoading = false)
            is GamesListState.Loading -> GamesList()
        }

    }
}

@Composable
fun GamesList(
    games: List<Game> = listOf(),
    isLoading: Boolean = true,
) {
    LazyColumn {
        items(games) { game ->
            GameItem(game = game, isLoading = isLoading)
        }
    }
}

@Composable
fun GameItem(
    game: Game,
    isLoading: Boolean = true,
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .size(56.dp)
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
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )

        Icon(Icons.Rounded.Person, contentDescription = "Number of players")
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = game.players,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
        )

        Spacer(modifier = Modifier.size(32.dp))

        Icon(Icons.Rounded.Refresh, contentDescription = "Number of players")
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = game.players,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    val images = Images(thumb = "", small = "", medium = "")
    SearchCoin(
        state = GamesListState.Success(listOf(
            Game(
                id = "id_1",
                name = "Game 1",
                images = images,
                playtime = "30",
                players = "3",
                yearPublished = 2000,
            ),
            Game(
                id = "id_2",
                name = "Game 2",
                images = images,
                playtime = "30",
                players = "3",
                yearPublished = 2000,
            ),
            Game(
                id = "id_3",
                name = "Game 3",
                images = images,
                playtime = "30",
                players = "3",
                yearPublished = 2000,
            ),
            Game(
                id = "id_4",
                name = "Game 4",
                images = images,
                playtime = "30",
                players = "3",
                yearPublished = 2000,
            ),
        ))
    )
}
