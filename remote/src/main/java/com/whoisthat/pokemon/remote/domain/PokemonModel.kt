package com.whoisthat.pokemon.remote.domain

import com.google.gson.annotations.SerializedName


data class PokemonModel (

  @SerializedName("id"                     ) var id                     : String?                = null,
  @SerializedName("name"                   ) var name                   : String?                = null,
  @SerializedName("supertype"              ) var supertype              : String?                = null,
  @SerializedName("subtypes"               ) var subtypes               : String?                = null,
  @SerializedName("hp"                     ) var hp                     : String?                = null,
  @SerializedName("types"                  ) var types                  : ArrayList<String>      = arrayListOf(),
  @SerializedName("evolvesFrom"            ) var evolvesFrom            : String?                = null,
  @SerializedName("attacks"                ) var attacks                : ArrayList<AttacksModel>     = arrayListOf(),
  @SerializedName("weaknesses"             ) var weaknesses             : ArrayList<WeaknessesModel>  = arrayListOf(),
  @SerializedName("resistances"            ) var resistances            : ArrayList<ResistancesModel> = arrayListOf(),
  @SerializedName("retreatCost"            ) var retreatCost            : ArrayList<String>      = arrayListOf(),
  @SerializedName("convertedRetreatCost"   ) var convertedRetreatCost   : Int?                   = null,
  @SerializedName("set"                    ) var set                    : SetModel?              = SetModel(),
  @SerializedName("number"                 ) var number                 : String?                = null,
  @SerializedName("artist"                 ) var artist                 : String?                = null,
  @SerializedName("rarity"                 ) var rarity                 : String?                = null,
  @SerializedName("flavorText"             ) var flavorText             : String?                = null,
  @SerializedName("nationalPokedexNumbers" ) var nationalPokedexNumbers : ArrayList<Int>         = arrayListOf(),
  @SerializedName("legalities"             ) var legalities             : LegalitiesModel?       = LegalitiesModel(),
  @SerializedName("images"                 ) var images                 : ImagesModel?           = ImagesModel(),
  @SerializedName("tcgplayer"              ) var tcgplayer              : TcgplayerModel         = TcgplayerModel(),
  @SerializedName("cardmarket"             ) var cardmarket             : MarketModel?           = MarketModel()

)