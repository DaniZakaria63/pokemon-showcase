package com.whoisthat.pokemon.remote.domain

import com.google.gson.annotations.SerializedName


data class PricesTcgModel (

  @SerializedName("holofoil" ) var holofoil: HolofoilModel? = null,
  @SerializedName("reverseHolofoil") var reverseHolofoil: ReverseHolofoilModel? = null,

  )