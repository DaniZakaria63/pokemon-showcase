package com.whoisthat.pokemon.remote.domain

import com.google.gson.annotations.SerializedName


data class SetModel (

    @SerializedName("id"           ) var id           : String?     = null,
    @SerializedName("name"         ) var name         : String?     = null,
    @SerializedName("series"       ) var series       : String?     = null,
    @SerializedName("printedTotal" ) var printedTotal : Int?        = null,
    @SerializedName("total"        ) var total        : Int?        = null,
    @SerializedName("legalities"   ) var legalities   : LegalitiesModel? = LegalitiesModel(),
    @SerializedName("ptcgoCode"    ) var ptcgoCode    : String?     = null,
    @SerializedName("releaseDate"  ) var releaseDate  : String?     = null,
    @SerializedName("updatedAt"    ) var updatedAt    : String?     = null,
    @SerializedName("images"       ) var images       : SetImagesModel?     = SetImagesModel()

)