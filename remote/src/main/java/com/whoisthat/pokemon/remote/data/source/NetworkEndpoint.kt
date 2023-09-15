package com.whoisthat.pokemon.remote.data.source

import com.whoisthat.pokemon.remote.domain.ResponseListCardModel
import com.whoisthat.pokemon.remote.domain.ResponseSingleCardModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkEndpoint {
    companion object {
        @JvmStatic
        val BASE_URL = "https://api.pokemontcg.io"
    }

    @GET("/v2/cards")
    suspend fun getCardWithParams(
        @Query("q") query: String? = "",
        @Query("page") page: Int? = 1,
        @Query("pageSize") pageSize: Int? = 20,
        @Query("orderBy") orderBy: String? = ""
    ): ResponseListCardModel

    @GET("/v2/cards/{id}")
    suspend fun getCardById(@Path("id") id: String?=""): ResponseSingleCardModel
}