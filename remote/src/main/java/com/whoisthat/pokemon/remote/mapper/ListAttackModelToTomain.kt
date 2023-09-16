package com.whoisthat.pokemon.remote.mapper

import com.whoisthat.pokemon.domain.domain.Attack
import com.whoisthat.pokemon.remote.domain.AttacksModel

fun ArrayList<AttacksModel>.toDomain(): List<Attack>{
    return map {
        Attack(
            name = it.name,
            cost = it.cost,
            convertedEnergyCost = it.convertedEnergyCost,
            damage = it.damage,
            text = it.text
        )
    }
}