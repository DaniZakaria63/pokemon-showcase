package com.whoisthat.pokemon.remote.domain

import com.google.gson.annotations.SerializedName


data class ImagesModel (

  @SerializedName("small" ) var small : String? = null,
  @SerializedName("large" ) var large : String? = null

)