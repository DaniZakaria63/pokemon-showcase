package com.whoisthat.pokemon.remote.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.whoisthat.pokemon.domain.domain.NetworkCardsQueryParams
import com.whoisthat.pokemon.domain.domain.Pokemon
import com.whoisthat.pokemon.remote.data.source.NetworkEndpoint
import com.whoisthat.pokemon.remote.mapper.toDomain
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RemotePokemonPagingSource constructor(
    private val queryParams: NetworkCardsQueryParams,
) : PagingSource<Int, Pokemon>() {
    @Inject lateinit var networkEndpoint: NetworkEndpoint

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val currentPage = params.key ?: 1
            val cards = networkEndpoint.getCardWithParams(
                query = queryParams.query,
                page = queryParams.page,
                pageSize = queryParams.pageSize,
                orderBy = queryParams.orderBy?.labeL
            )

            LoadResult.Page(
                data = cards.data.toDomain(),
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (cards.data.isEmpty()) null else cards.page!! + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

}