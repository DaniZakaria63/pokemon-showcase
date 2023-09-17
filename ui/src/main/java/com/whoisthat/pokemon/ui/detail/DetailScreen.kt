package com.whoisthat.pokemon.ui.detail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.whoisthat.pokemon.presenter.detail.navigator.DetailNavigator
import com.whoisthat.pokemon.presenter.detail.viewmodel.DetailViewModel
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@Composable
fun DetailScreen(
    pokemonId: String? = "-",
    viewModel: DetailViewModel = hiltViewModel(),
    navigatorCallback: (DetailNavigator) -> Unit,
) {
    var loadingProcess by remember{ mutableStateOf(false) }
    val pokemonState by viewModel.pokemonState.collectAsStateWithLifecycle()
    LaunchedEffect(pokemonId) {
        viewModel.getPokemonDetail(pokemonId)
    }

    LaunchedEffect(Unit) {
        viewModel.loadingStatus.collectLatest {
            Timber.i("Detail Loading Status $it")
            loadingProcess = it
        }

        viewModel.navigator.collectLatest {
            navigatorCallback(it)
        }

        viewModel.errorState.collectLatest {
            Timber.e(it)
        }
    }

    LazyColumn {
        item {
            SubcomposeAsyncImage(
                model = pokemonState.images?.large,
                contentDescription = "image.pokemon",
                onLoading = {viewModel.setLoadingStatus(true)},
                onError = {viewModel.setLoadingStatus(false)},
                onSuccess = {viewModel.setLoadingStatus(false)},
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
        }
        item {
            Text(text = pokemonState.name.toString())
        }
    }
}