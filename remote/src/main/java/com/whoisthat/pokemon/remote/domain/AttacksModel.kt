package com.whoisthat.pokemon.remote.domain

import com.google.gson.annotations.SerializedName


data class AttacksModel (

  @SerializedName("name"                ) var name                : String?           = null,
  @SerializedName("cost"                ) var cost                : ArrayList<String> = arrayListOf(),
  @SerializedName("convertedEnergyCost" ) var convertedEnergyCost : Int?              = null,
  @SerializedName("damage"              ) var damage              : String?           = null,
  @SerializedName("text"                ) var text                : String?           = null

)