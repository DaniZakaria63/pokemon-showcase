package com.whoisthat.pokemon.domain.domain

data class Pokemon(
    var id: String? = null,
    var name: String? = null,
    var supertype: String? = null,
    var subtypes: List<String>? = null,
    var hp: String? = null,
    var types: List<String> = listOf(),
    var evolvesFrom: String? = null,
    var attacks: List<Attack> = listOf(),
    var weaknesses: List<Weaknesses> = listOf(),
    var resistances: List<Resistances> = listOf(),
    var retreatCost: List<String> = listOf(),
    var convertedRetreatCost: Int? = null,
    var set: Set? = Set(),
    var number: String? = null,
    var artist: String? = null,
    var rarity: String? = null,
    var flavorText: String? = null,
    var nationalPokedexNumbers: List<Int> = listOf(),
    var legalities: Legalities? = Legalities(),
    var images: Images? = Images(),
    var tcgplayer: TcgPlayer? = TcgPlayer(),
    var cardmarket: Market? = Market()
)