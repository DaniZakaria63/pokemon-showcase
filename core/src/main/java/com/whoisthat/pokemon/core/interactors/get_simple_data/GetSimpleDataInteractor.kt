package com.whoisthat.pokemon.core.interactors.get_simple_data

import com.whoisthat.pokemon.core.data.source.PokemonRepository
import com.whoisthat.pokemon.domain.domain.Pokemon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSimpleDataInteractor @Inject constructor(
    val repository: PokemonRepository
) {
    suspend operator fun invoke() : Flow<Result<List<Pokemon>>> {
        return repository.getSimpleData()
    }
}