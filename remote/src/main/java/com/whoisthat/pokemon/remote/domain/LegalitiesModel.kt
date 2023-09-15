package com.whoisthat.pokemon.remote.domain

import com.google.gson.annotations.SerializedName


data class LegalitiesModel (

  @SerializedName("unlimited" ) var unlimited : String? = null

)