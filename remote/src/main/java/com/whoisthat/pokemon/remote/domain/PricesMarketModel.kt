package com.whoisthat.pokemon.remote.domain

import com.google.gson.annotations.SerializedName


data class PricesMarketModel (

  @SerializedName("averageSellPrice" ) var averageSellPrice : Double? = null,
  @SerializedName("lowPrice"         ) var lowPrice         : Double? = null,
  @SerializedName("trendPrice"       ) var trendPrice       : Double? = null,
  @SerializedName("germanProLow"     ) var germanProLow     : Int?    = null,
  @SerializedName("suggestedPrice"   ) var suggestedPrice   : Int?    = null,
  @SerializedName("reverseHoloSell"  ) var reverseHoloSell  : Double? = null,
  @SerializedName("reverseHoloLow"   ) var reverseHoloLow   : Double? = null,
  @SerializedName("reverseHoloTrend" ) var reverseHoloTrend : Double? = null,
  @SerializedName("lowPriceExPlus"   ) var lowPriceExPlus   : Int?    = null,
  @SerializedName("avg1"             ) var avg1             : Int?    = null,
  @SerializedName("avg7"             ) var avg7             : Double? = null,
  @SerializedName("avg30"            ) var avg30            : Double? = null,
  @SerializedName("reverseHoloAvg1"  ) var reverseHoloAvg1  : Int?    = null,
  @SerializedName("reverseHoloAvg7"  ) var reverseHoloAvg7  : Double? = null,
  @SerializedName("reverseHoloAvg30" ) var reverseHoloAvg30 : Int?    = null

)