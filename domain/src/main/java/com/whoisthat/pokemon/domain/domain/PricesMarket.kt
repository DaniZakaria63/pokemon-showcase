package com.whoisthat.pokemon.domain.domain

data class PricesMarket(
    var averageSellPrice: Double? = null,
    var lowPrice: Double? = null,
    var trendPrice: Double? = null,
    var germanProLow: Double? = null,
    var suggestedPrice: Double? = null,
    var reverseHoloSell: Double? = null,
    var reverseHoloLow: Double? = null,
    var reverseHoloTrend: Double? = null,
    var lowPriceExPlus: Double? = null,
    var avg1: Double? = null,
    var avg7: Double? = null,
    var avg30: Double? = null,
    var reverseHoloAvg1: Double? = null,
    var reverseHoloAvg7: Double? = null,
    var reverseHoloAvg30: Double? = null
)