package com.whoisthat.pokemon.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.whoisthat.pokemon.core.data.source.PokemonRepository
import com.whoisthat.pokemon.domain.domain.NetworkCardsQueryParams
import com.whoisthat.pokemon.domain.domain.Pokemon
import com.whoisthat.pokemon.domain.source.DispatcherProvider
import com.whoisthat.pokemon.local.data.source.DataStore
import com.whoisthat.pokemon.paging.data.repository.PokemonPagingRepository
import com.whoisthat.pokemon.remote.data.source.NetworkService
import com.whoisthat.pokemon.remote.domain.PokemonModel
import com.whoisthat.pokemon.remote.mapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultPokemonRepository @Inject constructor(
    val pokemonDataStore: DataStore,
    val networkService: NetworkService,
    val dispatcherProvider: DispatcherProvider
) : PokemonRepository {

    override suspend fun getSimpleData(): Flow<Result<List<Pokemon>>> {
        return networkService.requestSimpleAPI()
            .map { data: List<PokemonModel> -> Result.success(data.toDomain()) }
            .catch { emit(Result.failure(it)) }
            .flowOn(dispatcherProvider.io)
    }

    override suspend fun getPokemonListWithSearch(params: NetworkCardsQueryParams): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(pageSize = params.pageSize ?: 20),
            pagingSourceFactory = {
                // TODO: Assist Injection for custom params
                PokemonPagingRepository(params, networkService, pokemonDataStore, dispatcherProvider)
            }
        ).flow
    }

    override suspend fun getPokemonDetailById(pokemonId: String): Flow<Result<Pokemon>> {
        return networkService.requestPokemonDetailById(pokemonId)
            .map { data: PokemonModel -> Result.success(data.toDomain()) }
            .catch { emit(Result.failure(it)) }
            .flowOn(dispatcherProvider.io)
    }
}