package com.whoisthat.pokemon.local.data.repository

import com.whoisthat.pokemon.local.data.dao.PokemonDao
import com.whoisthat.pokemon.local.data.source.DataStore
import javax.inject.Inject

class PokemonDataStore @Inject constructor(
    pokemonDao: PokemonDao
):DataStore {

}