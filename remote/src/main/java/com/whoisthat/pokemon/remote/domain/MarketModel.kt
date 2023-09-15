package com.whoisthat.pokemon.remote.domain

import com.google.gson.annotations.SerializedName


data class MarketModel (

  @SerializedName("url"       ) var url       : String? = null,
  @SerializedName("updatedAt" ) var updatedAt : String? = null,
  @SerializedName("prices"    ) var prices    : PricesModel? = PricesModel()

)