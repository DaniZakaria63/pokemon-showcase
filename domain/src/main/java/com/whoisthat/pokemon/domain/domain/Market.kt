package com.whoisthat.pokemon.domain.domain

data class Market(
    var url       : String? = null,
    var updatedAt : String? = null,
    var prices    : Prices? = Prices()
) {
}