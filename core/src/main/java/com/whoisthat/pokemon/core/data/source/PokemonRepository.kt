package com.whoisthat.pokemon.core.data.source

import androidx.paging.PagingData
import com.whoisthat.pokemon.domain.domain.NetworkCardsQueryParams
import com.whoisthat.pokemon.domain.domain.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getSimpleData():Flow<Result<List<Pokemon>>>
    suspend fun getPokemonListWithSearch(params: NetworkCardsQueryParams): Flow<PagingData<Pokemon>>
}