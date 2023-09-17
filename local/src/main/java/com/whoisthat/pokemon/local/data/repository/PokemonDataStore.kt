package com.whoisthat.pokemon.local.data.repository

import com.whoisthat.pokemon.domain.domain.Pokemon
import com.whoisthat.pokemon.domain.source.DispatcherProvider
import com.whoisthat.pokemon.local.data.dao.PokemonDao
import com.whoisthat.pokemon.local.data.source.DataStore
import com.whoisthat.pokemon.local.mapper.toDatabase
import com.whoisthat.pokemon.local.mapper.toDomain
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonDataStore @Inject constructor(
    val pokemonDao: PokemonDao,
    val dispatcherProvider: DispatcherProvider,
) : DataStore {
    override suspend fun getAllPokemons(): List<Pokemon> {
        return withContext(dispatcherProvider.io) {
            pokemonDao.getAllPokemonData()
                .map {
                    it.toDomain()
                }
        }
    }

    override suspend fun saveAllPokemonsPreview(pokemon: List<Pokemon>) {
        coroutineScope{
            launch(dispatcherProvider.io) {
                pokemonDao.insertAllPokemonData(pokemon.toDatabase())
            }
        }
    }
}