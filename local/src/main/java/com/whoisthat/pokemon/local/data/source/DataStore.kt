package com.whoisthat.pokemon.local.data.source

import com.whoisthat.pokemon.domain.domain.Pokemon
import kotlinx.coroutines.flow.Flow

interface DataStore {
    suspend fun getAllPokemons(): List<Pokemon>
    suspend fun saveAllPokemonsPreview(pokemon: List<Pokemon>)
}