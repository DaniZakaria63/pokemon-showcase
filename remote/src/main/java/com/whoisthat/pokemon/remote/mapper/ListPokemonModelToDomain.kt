package com.whoisthat.pokemon.remote.mapper

import com.whoisthat.pokemon.domain.domain.Images
import com.whoisthat.pokemon.domain.domain.Legalities
import com.whoisthat.pokemon.domain.domain.Pokemon
import com.whoisthat.pokemon.remote.domain.PokemonModel

fun List<PokemonModel>.toDomain(): List<Pokemon> {
    return map {
        Pokemon(
            id = it.id,
            name = it.name,
            supertype = it.supertype,
            subtypes = it.subtypes,
            hp = it.hp,
            types = it.types,
            evolvesFrom = it.evolvesFrom,
            attacks = it.attacks.toDomain(),
            weaknesses = it.weaknesses.toDomain(),
            resistances = it.resistances.toDomain(),
            retreatCost = it.retreatCost,
            convertedRetreatCost = it.convertedRetreatCost,
            set = it.set?.toDomain(),
            number = it.number,
            artist = it.artist,
            rarity = it.rarity,
            flavorText = it.flavorText,
            nationalPokedexNumbers = it.nationalPokedexNumbers,
            legalities = it.legalities?.toDomain(),
            images = it.images?.toDomain(),
            tcgplayer = it.tcgplayer.toDomain(),
            cardmarket = it.cardmarket?.toDomain()
        )
    }
}