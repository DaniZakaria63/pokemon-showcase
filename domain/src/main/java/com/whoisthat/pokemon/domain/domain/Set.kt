package com.whoisthat.pokemon.domain.domain

data class Set(
    var id: String? = null,
    var name: String? = null,
    var series: String? = null,
    var printedTotal: Int? = null,
    var total: Int? = null,
    var legalities: Legalities? = Legalities(),
    var ptcgoCode: String? = null,
    var releaseDate: String? = null,
    var updatedAt: String? = null,
    var images: Images? = Images()
) {
    data class Images(
        var symbol: String? = null,
        var logo: String? = null,
    )
}