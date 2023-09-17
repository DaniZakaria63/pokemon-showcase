package com.whoisthat.pokemon.remote.data.repository

import com.whoisthat.pokemon.domain.domain.NetworkCardsQueryParams
import com.whoisthat.pokemon.remote.data.source.NetworkEndpoint
import com.whoisthat.pokemon.remote.data.source.NetworkService
import com.whoisthat.pokemon.remote.domain.PokemonModel
import com.whoisthat.pokemon.remote.domain.ResponseListCardModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultNetworkService @Inject constructor(
    val networkEndpoint: NetworkEndpoint
) : NetworkService {
    override fun requestSimpleAPI(): Flow<List<PokemonModel>> = flow {
        val data: ResponseListCardModel = networkEndpoint.getCardWithParams()
        emit(data.data)
    }

    override suspend fun requestPokemonsDataWithParams(query: NetworkCardsQueryParams): ResponseListCardModel {
        return networkEndpoint.getCardWithParams(
            query = query.query,
            page = query.page,
            pageSize = query.pageSize,
            orderBy = query.orderBy?.labeL
        )
    }
}