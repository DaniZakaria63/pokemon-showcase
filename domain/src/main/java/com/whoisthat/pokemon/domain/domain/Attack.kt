package com.whoisthat.pokemon.domain.domain

data class Attack(
    var name                : String?           = null,
    var cost                : List<String> = listOf(),
    var convertedEnergyCost : Int?              = null,
    var damage              : String?           = null,
    var text                : String?           = null
)