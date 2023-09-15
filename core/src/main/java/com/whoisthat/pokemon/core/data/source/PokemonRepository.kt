package com.whoisthat.pokemon.core.data.source

import com.whoisthat.pokemon.domain.domain.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getSimpleData():Flow<Result<List<Pokemon>>>
}