package com.whoisthat.pokemon.remote.domain

import com.google.gson.annotations.SerializedName

data class ResponseSingleCardModel(
    @SerializedName("data") val data: PokemonModel = PokemonModel()
)