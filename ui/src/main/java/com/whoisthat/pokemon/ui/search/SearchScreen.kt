package com.whoisthat.pokemon.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.whoisthat.pokemon.domain.domain.Pokemon
import com.whoisthat.pokemon.presenter.search.navigator.SearchNavigator
import com.whoisthat.pokemon.presenter.search.viewmodel.SearchViewModel
import com.whoisthat.pokemon.ui.theme.Dimens
import com.whoisthat.pokemon.ui.widget.shimmerBrush
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navigatorCallback: (SearchNavigator) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val pokemonPagingState: LazyPagingItems<Pokemon> =
        viewModel.pokemonPagingState.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        viewModel.searchPagingPokemonsWithParams()
    }

    LaunchedEffect(true) {
        viewModel.navigation.collectLatest {
            navigatorCallback(it)
        }

        viewModel.errorState.collectLatest {
            Timber.e("Error Happened", it)
        }

        viewModel.loadingStatus.collectLatest {
            Timber.i("Loading Data Status $it")
        }
    }

    var searchText by remember { mutableStateOf("") }
    var searchClearActive by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        item {
            TextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    searchClearActive = it.isNotEmpty()
                    coroutineScope.launch { viewModel.queryHandler(it) }
                },
                label = { Text(text = "Search") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        contentDescription = "search.icon"
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
        items(pokemonPagingState.itemCount) { index ->
            val pokemon = pokemonPagingState[index]
            Card(onClick = {
                coroutineScope.launch {
                    viewModel.navigateTo(
                        SearchNavigator.NavigateToDetailScreen(
                            pokemon?.id ?: "err#1"
                        )
                    )
                }
            }, modifier = Modifier.padding(horizontal = Dimens.normal, vertical = Dimens.medium)) {
                Row {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(
                                start = Dimens.normal,
                                top = Dimens.normal,
                                bottom = Dimens.normal
                            )
                    ) {
                        Text(
                            "${pokemon?.name}",
                            fontWeight = FontWeight.Bold,
                            fontSize = Dimens.Text.title,
                            fontFamily = FontFamily.Serif,
                            modifier = Modifier.padding(horizontal = Dimens.normal, vertical = Dimens.normal)
                        )
                        Text("HP ${pokemon?.hp}",
                            fontSize = Dimens.Text.bodyMedium,
                            fontFamily = FontFamily.SansSerif,
                            modifier = Modifier.padding(horizontal = Dimens.normal)
                        )
                        Text("Rarity ${pokemon?.rarity}",
                            fontSize = Dimens.Text.bodyMedium,
                            fontFamily = FontFamily.SansSerif,
                            modifier = Modifier.padding(horizontal = Dimens.normal)
                        )
                    }
                    AsyncImage(
                        model = pokemonPagingState[index]?.images?.small,
                        contentDescription = "pokemon.image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(200.dp)
                            .width(170.dp)
                            .background(shimmerBrush())
                    )
                }

            }
        }

        pokemonPagingState.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Loading",
                                color = MaterialTheme.colorScheme.primary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            CircularProgressIndicator(Modifier.padding(top = 10.dp))
                        }
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = pokemonPagingState.loadState.refresh as LoadState.Error
                    Timber.e(error.error.cause)
                    item { Text(text = "Refresh Loading State, But Error Happened") }
                }

                loadState.append is LoadState.Loading -> {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .wrapContentWidth(Alignment.CenterHorizontally)
                        )
                    }
                }

                loadState.append is LoadState.Error -> {
                    item { Text(text = "Append Loading State, But Error Happened") }
                }
            }
        }
    }
}