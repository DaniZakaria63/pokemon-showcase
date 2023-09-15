package com.whoisthat.pokemon.domain.domain

data class Prices(
    var averageSellPrice: Double? = null,
    var lowPrice: Double? = null,
    var trendPrice: Double? = null,
    var germanProLow: Int? = null,
    var suggestedPrice: Int? = null,
    var reverseHoloSell: Double? = null,
    var reverseHoloLow: Double? = null,
    var reverseHoloTrend: Double? = null,
    var lowPriceExPlus: Int? = null,
    var avg1: Int? = null,
    var avg7: Double? = null,
    var avg30: Double? = null,
    var reverseHoloAvg1: Int? = null,
    var reverseHoloAvg7: Double? = null,
    var reverseHoloAvg30: Int? = null
)