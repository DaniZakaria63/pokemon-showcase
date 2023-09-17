package com.whoisthat.pokemon.remote.data.source

import com.whoisthat.pokemon.domain.domain.NetworkCardsQueryParams
import com.whoisthat.pokemon.remote.domain.PokemonModel
import com.whoisthat.pokemon.remote.domain.ResponseListCardModel
import kotlinx.coroutines.flow.Flow

interface NetworkService {
    fun requestSimpleAPI(): Flow<List<PokemonModel>>
    suspend fun requestPokemonsDataWithParams(query: NetworkCardsQueryParams): Result<ResponseListCardModel>
    suspend fun requestPokemonDetailById(pokemonId: String): Flow<PokemonModel>
}