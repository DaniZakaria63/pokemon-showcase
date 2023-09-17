package com.whoisthat.pokemon.remote.mapper

import com.whoisthat.pokemon.domain.domain.Images
import com.whoisthat.pokemon.domain.domain.Legalities
import com.whoisthat.pokemon.domain.domain.Pokemon
import com.whoisthat.pokemon.remote.domain.ImagesModel
import com.whoisthat.pokemon.remote.domain.LegalitiesModel
import com.whoisthat.pokemon.remote.domain.PokemonModel

fun PokemonModel.toDomain(): Pokemon {
    return Pokemon(
        id,
        name,
        supertype,
        subtypes,
        hp,
        types,
        evolvesFrom,
        attacks.toDomain(),
        weaknesses.toDomain(),
        resistances.toDomain(),
        retreatCost,
        convertedRetreatCost,
        set?.toDomain(),
        number, artist, rarity, flavorText, nationalPokedexNumbers,
        legalities?.toDomain(),
        images?.toDomain(),
        tcgplayer.toDomain(),
        cardmarket?.toDomain(),
    )
}

fun LegalitiesModel.toDomain(): Legalities {
    return Legalities(unlimited)
}

fun ImagesModel.toDomain(): Images {
    return Images(small, large)
}