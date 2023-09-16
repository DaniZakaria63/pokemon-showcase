package com.whoisthat.pokemon.core.interactors.get_cards_by_search

import androidx.paging.PagingData
import com.whoisthat.pokemon.core.data.source.PokemonRepository
import com.whoisthat.pokemon.domain.domain.NetworkCardsQueryParams
import com.whoisthat.pokemon.domain.domain.Pokemon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCardsPagingBySearchInteractor @Inject constructor(
    val repository: PokemonRepository
){
    suspend operator fun invoke(query: NetworkCardsQueryParams): Flow<PagingData<Pokemon>> {
        return repository.getPokemonListWithSearch(query)
    }
}