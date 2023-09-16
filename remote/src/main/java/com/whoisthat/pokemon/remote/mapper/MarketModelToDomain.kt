package com.whoisthat.pokemon.remote.mapper

import com.whoisthat.pokemon.domain.domain.Market
import com.whoisthat.pokemon.remote.domain.MarketModel


fun MarketModel.toDomain(): Market=
    Market(url, updatedAt, prices?.toDomain())

