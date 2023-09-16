package com.whoisthat.pokemon.remote.mapper

import com.whoisthat.pokemon.domain.domain.Holofoil
import com.whoisthat.pokemon.domain.domain.PricesTcg
import com.whoisthat.pokemon.domain.domain.ReverseHolofoil
import com.whoisthat.pokemon.remote.domain.HolofoilModel
import com.whoisthat.pokemon.remote.domain.PricesTcgModel
import com.whoisthat.pokemon.remote.domain.ReverseHolofoilModel

fun PricesTcgModel.toDomain(): PricesTcg =
    PricesTcg(holofoil?.toDomain(), reverseHolofoil?.toDomain())

fun HolofoilModel.toDomain(): Holofoil =
    Holofoil(low, mid, high, market, directLow)

fun ReverseHolofoilModel.toDomain(): ReverseHolofoil =
    ReverseHolofoil(low, mid, high, market, directLow)