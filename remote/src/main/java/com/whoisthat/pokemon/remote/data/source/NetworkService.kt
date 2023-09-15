package com.whoisthat.pokemon.remote.data.source

import com.whoisthat.pokemon.remote.domain.PokemonModel
import kotlinx.coroutines.flow.Flow

interface NetworkService {
    fun requestSimpleAPI(): Flow<List<PokemonModel>>
}