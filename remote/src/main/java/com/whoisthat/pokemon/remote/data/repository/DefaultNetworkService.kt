package com.whoisthat.pokemon.remote.data.repository

import com.whoisthat.pokemon.domain.domain.NetworkCardsQueryParams
import com.whoisthat.pokemon.remote.data.source.NetworkEndpoint
import com.whoisthat.pokemon.remote.data.source.NetworkService
import com.whoisthat.pokemon.remote.domain.PokemonModel
import com.whoisthat.pokemon.remote.domain.ResponseListCardModel
import com.whoisthat.pokemon.remote.domain.ResponseSingleCardModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class DefaultNetworkService @Inject constructor(
    val networkEndpoint: NetworkEndpoint
) : NetworkService {
    override fun requestSimpleAPI(): Flow<List<PokemonModel>> = flow {
        val response = networkEndpoint.getCardWithParams()
        if (response.isSuccessful) {
            val data: ResponseListCardModel? = response.body()
            emit(data?.data ?: listOf())
        } else {
            //TODO: Add Error Handler
        }
    }

    override suspend fun requestPokemonsDataWithParams(query: NetworkCardsQueryParams): Result<ResponseListCardModel> {
        val response = networkEndpoint.getCardWithParams(
            query = query.query,
            page = query.page,
            pageSize = query.pageSize,
            orderBy = query.orderBy?.labeL
        )
        return if (response.isSuccessful) Result.success(response.body()!!)
        else Result.failure(
            IOException(response.errorBody().toString())
        )
    }

    override suspend fun requestPokemonDetailById(pokemonId: String): Flow<PokemonModel> = flow {
        val response = networkEndpoint.getCardById(pokemonId)
        if (response.isSuccessful) {
            val data: ResponseSingleCardModel? = response.body()
            emit(data?.data!!)
        } else {
            //TODO: Add Error Handler
        }
    }
}