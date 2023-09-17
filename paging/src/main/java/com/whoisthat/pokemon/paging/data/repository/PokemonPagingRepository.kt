package com.whoisthat.pokemon.paging.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.whoisthat.pokemon.domain.coroutine.coroutineHandler
import com.whoisthat.pokemon.domain.domain.NetworkCardsQueryParams
import com.whoisthat.pokemon.domain.domain.Pokemon
import com.whoisthat.pokemon.domain.source.DispatcherProvider
import com.whoisthat.pokemon.local.data.source.DataStore
import com.whoisthat.pokemon.local.mapper.toDatabase
import com.whoisthat.pokemon.remote.data.source.NetworkService
import com.whoisthat.pokemon.remote.domain.ResponseListCardModel
import com.whoisthat.pokemon.remote.mapper.toDomain
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.EntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import timber.log.Timber

class PokemonPagingRepository constructor(
    val queryParams: NetworkCardsQueryParams,
    val pokemonNetworkService: NetworkService,
    val pokemonDataStore: DataStore,
    val dispatcherProvider: DispatcherProvider,
) : PagingSource<Int, Pokemon>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> =
        withContext(dispatcherProvider.io) {
            val pokemonRemoteData = async(dispatcherProvider.io + coroutineHandler) {
                pokemonNetworkService.requestPokemonsDataWithParams(queryParams)
            }
            val pokemonLocalData = async(dispatcherProvider.io + coroutineHandler) {
                pokemonDataStore.getAllPokemons()
            }
            var pokemons: ResponseListCardModel? = null
            var localPokemon: List<Pokemon>? = null
            try {
                val currentPage = params.key ?: 1
                queryParams.page = currentPage

                val pokemonsResponse = pokemonRemoteData.await()
                if (pokemonsResponse.isSuccess) {
                    pokemons = pokemonsResponse.getOrNull() ?: ResponseListCardModel()
                    pokemonDataStore.saveAllPokemonsPreview(pokemons.data.toDomain())
                } else {
                    localPokemon = pokemonLocalData.await()
                }

                LoadResult.Page(
                    data = pokemons?.data?.toDomain()?.toList() ?: localPokemon?.toList()!!,
                    prevKey = if (currentPage == 1) null else currentPage.minus(1),
                    nextKey = if (pokemons?.data?.isEmpty() != false) null else pokemons.page?.plus(
                        1
                    )
                )
            } catch (e: Exception) {
                Timber.e(e)
                LoadResult.Error(e)
            }
        }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition
    }
}