package com.whoisthat.pokemon.remote.domain

import com.google.gson.annotations.SerializedName

data class SetImagesModel(
    @SerializedName("symbol") val symbol: String? = null,
    @SerializedName("logo") val logo: String? = null
) {
}