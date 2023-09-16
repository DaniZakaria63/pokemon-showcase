package com.whoisthat.pokemon.remote.mapper

import com.whoisthat.pokemon.domain.domain.PricesMarket
import com.whoisthat.pokemon.remote.domain.PricesMarketModel


fun PricesMarketModel.toDomain(): PricesMarket = PricesMarket(
    averageSellPrice,
    lowPrice,
    trendPrice,
    germanProLow,
    suggestedPrice,
    reverseHoloSell,
    reverseHoloLow,
    reverseHoloTrend,
    lowPriceExPlus,
    avg1,
    avg7,
    avg30,
    reverseHoloAvg1,
    reverseHoloAvg7,
    reverseHoloAvg30
)