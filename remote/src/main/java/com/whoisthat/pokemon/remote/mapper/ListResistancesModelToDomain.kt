package com.whoisthat.pokemon.remote.mapper

import com.whoisthat.pokemon.domain.domain.Resistances
import com.whoisthat.pokemon.remote.domain.ResistancesModel

fun ArrayList<ResistancesModel>.toDomain(): List<Resistances>{
    return map {
        Resistances(it.type, it.value)
    }
}