package com.whoisthat.pokemon.remote.mapper

import com.whoisthat.pokemon.domain.domain.Weaknesses
import com.whoisthat.pokemon.remote.domain.WeaknessesModel

class ListWeaknessModelToDomain {
}

fun ArrayList<WeaknessesModel>.toDomain(): List<Weaknesses>{
    return map {
        Weaknesses(it.type, it.value)
    }
}