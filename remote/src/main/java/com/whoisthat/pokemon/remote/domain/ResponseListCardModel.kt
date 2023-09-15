package com.whoisthat.pokemon.remote.domain

import com.google.gson.annotations.SerializedName


data class ResponseListCardModel (

    @SerializedName("data"       ) var data       : ArrayList<PokemonModel> = arrayListOf(),
    @SerializedName("page"       ) var page       : Int?            = null,
    @SerializedName("pageSize"   ) var pageSize   : Int?            = null,
    @SerializedName("count"      ) var count      : Int?            = null,
    @SerializedName("totalCount" ) var totalCount : Int?            = null

)