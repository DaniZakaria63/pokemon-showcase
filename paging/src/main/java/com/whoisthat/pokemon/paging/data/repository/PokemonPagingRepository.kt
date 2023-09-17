package com.whoisthat.pokemon.paging.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.whoisthat.pokemon.domain.domain.NetworkCardsQueryParams
import com.whoisthat.pokemon.domain.domain.Pokemon
import com.whoisthat.pokemon.local.data.source.DataStore
import com.whoisthat.pokemon.remote.data.source.NetworkService
import com.whoisthat.pokemon.remote.mapper.toDomain
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.EntryPoint
import timber.log.Timber

class PokemonPagingRepository constructor(
    val queryParams: NetworkCardsQueryParams,
    val pokemonNetworkService: NetworkService,
    val pokemonDataStore: DataStore,
) : PagingSource<Int, Pokemon>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val currentPage = params.key ?: 1
            queryParams.page = currentPage
            val pokemons = pokemonNetworkService.requestPokemonsDataWithParams(queryParams)
            LoadResult.Page(
                data = pokemons.data.toDomain().toList(),
                prevKey = if (currentPage == 1) null else currentPage.minus(1),
                nextKey = if (pokemons.data.isEmpty()) null else pokemons.page?.plus(1)
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