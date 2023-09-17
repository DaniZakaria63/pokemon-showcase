package com.whoisthat.pokemon.domain.domain

data class NetworkCardsQueryParams(
    val query: String? = "",
    var page: Int? = 1,
    val pageSize: Int? = 20,
    val orderBy: OrderBy? = OrderBy.NAME
) {
    enum class OrderBy(val labeL: String){
        NAME("name"),
        NUMBER("number")
    }
}