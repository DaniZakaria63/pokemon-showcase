package com.whoisthat.pokemon.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.whoisthat.pokemon.presenter.detail.navigator.DetailNavigator
import com.whoisthat.pokemon.presenter.detail.viewmodel.DetailViewModel
import com.whoisthat.pokemon.ui.theme.Dimens
import com.whoisthat.pokemon.ui.widget.shimmerBrush
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailScreen(
    pokemonId: String? = "-",
    viewModel: DetailViewModel = hiltViewModel(),
    navigatorCallback: (DetailNavigator) -> Unit,
) {
    var loadingProcess by remember { mutableStateOf(false) }
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
                onLoading = { viewModel.setLoadingStatus(true) },
                onError = { viewModel.setLoadingStatus(false) },
                onSuccess = { viewModel.setLoadingStatus(false) },
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .background(shimmerBrush())
            )
        }
        item {
            Text(
                text = pokemonState.name.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = Dimens.Text.title,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(
                    start = Dimens.medium,
                    end = Dimens.normal,
                    top = Dimens.large,
                    bottom = Dimens.normal
                )
            )
        }
        item {
            FlowColumn {
                Text(
                    text = pokemonState.flavorText.toString(),
                    fontSize = Dimens.Text.bodyMedium,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding(
                        start = Dimens.medium,
                        end = Dimens.normal,
                        top = Dimens.large,
                        bottom = Dimens.normal
                    ).fillMaxWidth()
                )
                Text(
                    text = "HP: ${pokemonState.hp.toString()}",
                    fontSize = Dimens.Text.bodyMedium,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding(
                        start = Dimens.medium,
                        end = Dimens.normal,
                        top = Dimens.large,
                        bottom = Dimens.normal
                    )
                )
                Text(
                    text = "Rarity: ${pokemonState.rarity.toString()}",
                    fontSize = Dimens.Text.bodyMedium,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding(
                        start = Dimens.medium,
                        end = Dimens.normal,
                        top = Dimens.large,
                        bottom = Dimens.normal
                    )
                )
                Text(
                    text = "Artist: ${pokemonState.artist.toString()}",
                    fontSize = Dimens.Text.bodyMedium,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding(
                        start = Dimens.medium,
                        end = Dimens.normal,
                        top = Dimens.large,
                        bottom = Dimens.normal
                    )
                )
            }
        }
    }
}