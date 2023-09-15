package com.whoisthat.pokemon.remote.domain

import com.google.gson.annotations.SerializedName


data class HolofoilModel (

  @SerializedName("low"       ) var low       : Double? = null,
  @SerializedName("mid"       ) var mid       : Double? = null,
  @SerializedName("high"      ) var high      : Double? = null,
  @SerializedName("market"    ) var market    : Double? = null,
  @SerializedName("directLow" ) var directLow : String? = null

)