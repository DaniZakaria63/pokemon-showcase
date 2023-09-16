package com.whoisthat.pokemon.domain.domain

data class TcgPlayer(

    var url       : String? = null,
    var updatedAt : String? = null,
    var prices    : PricesTcg? = PricesTcg()
) {
}