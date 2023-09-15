package com.whoisthat.pokemon.remote.domain

import com.google.gson.annotations.SerializedName


data class WeaknessesModel (

  @SerializedName("type"  ) var type  : String? = null,
  @SerializedName("value" ) var value : String? = null

)