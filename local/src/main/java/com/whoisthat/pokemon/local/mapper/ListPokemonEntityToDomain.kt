package com.whoisthat.pokemon.local.mapper

import com.whoisthat.pokemon.domain.domain.Images
import com.whoisthat.pokemon.domain.domain.Pokemon
import com.whoisthat.pokemon.local.domain.PokemonEntity

fun List<PokemonEntity>.toDomain(): List<Pokemon> {
    return map {
        Pokemon(
            id = it._id,
            name = it.name,
            supertype = it.superType,
            hp = it.hp,
            images = Images(small = it.image),
            rarity = it.rarity
        )
    }
}

fun PokemonEntity.toDomain(): Pokemon{
    return Pokemon(
        id = _id,
        name = name,
        supertype = superType,
        hp = hp,
        images = Images(small = image),
        rarity = rarity
    )
}

fun List<Pokemon>.toDatabase(): List<PokemonEntity>{
    return map {
        PokemonEntity(
            _id = it.id.toString(),
            name = it.name,
            superType = it.supertype,
            hp = it.hp,
            image = it.images?.small.toString(),
            rarity = it.rarity
        )
    }
}