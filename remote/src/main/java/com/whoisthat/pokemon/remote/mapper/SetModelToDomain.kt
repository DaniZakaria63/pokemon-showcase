package com.whoisthat.pokemon.remote.mapper

import com.whoisthat.pokemon.domain.domain.Images
import com.whoisthat.pokemon.domain.domain.Legalities
import com.whoisthat.pokemon.domain.domain.Set
import com.whoisthat.pokemon.remote.domain.SetModel

fun SetModel.toDomain(): Set = Set(
    id, name, series, printedTotal, total,
    legalities = Legalities(legalities?.unlimited),
    ptcgoCode, releaseDate, updatedAt,
    images = Set.Images(images?.symbol, images?.logo),
    )