package com.martacalo.letsplay.ui.library

import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.martacalo.letsplay.R
import com.martacalo.letsplay.ui.library.contract.LibraryNavigationEvent


@Composable
fun LibraryRoute(
    modifier: Modifier = Modifier,
    viewModel: LibraryViewModel = viewModel(),
    navigateTo: (LibraryNavigationEvent) -> Unit,
) {

    Library { navigateTo(it) }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Library(
    modifier: Modifier = Modifier,
    onAddGame: (LibraryNavigationEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAddGame(LibraryNavigationEvent.NavigateToSearch) },
            ) {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) {

    }
}

@Preview
@Composable
fun PreviewLibrary() {
    Library { }
}
