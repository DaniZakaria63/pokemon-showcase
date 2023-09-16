package com.whoisthat.pokemon.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.whoisthat.pokemon.domain.domain.Pokemon
import com.whoisthat.pokemon.presenter.PokemonViewModel
import com.whoisthat.pokemon.ui.theme.PokemonShowcaseTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PokemonShowcaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val viewModel: PokemonViewModel = hiltViewModel()

                    val pagingData:LazyPagingItems<Pokemon> = viewModel.pokemonPagingState.collectAsLazyPagingItems()
                    val data by viewModel.pokemonList.collectAsStateWithLifecycle(initialValue = null)
                    val loading by viewModel.loadingStatus.collectAsStateWithLifecycle(initialValue = null)
                    val errorState by viewModel.errorState.collectAsStateWithLifecycle(initialValue = null)

                    LaunchedEffect(true){
                        viewModel.getSimpleData()
                    }

                    LaunchedEffect(Unit){
                        viewModel.searchPagingPokemonsWithParams()
                    }

                    LaunchedEffect(pagingData){
                        Timber.i("UI Paging Data: ${pagingData.itemCount}")
                    }

                    LaunchedEffect(data){
                        Timber.i("Data: $data")
                    }

                    LaunchedEffect(loading){
                        Timber.i("Loading Process $loading")
                    }

                    LaunchedEffect(errorState){
                        Timber.i("Error State: $errorState")
                    }
                }
            }
        }
    }
}