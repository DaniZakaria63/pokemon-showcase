package com.whoisthat.pokemon.remote.mapper

import com.whoisthat.pokemon.domain.domain.Pokemon
import com.whoisthat.pokemon.remote.domain.PokemonModel

fun List<PokemonModel>.toDomain(): List<Pokemon>{
    return map { Pokemon(

    ) }
}