package com.whoisthat.pokemon.core.interactors.get_pokemon_detail_by_id

import com.whoisthat.pokemon.core.data.source.PokemonRepository
import com.whoisthat.pokemon.domain.domain.Pokemon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonDetailByIdInteractor @Inject constructor(
    val repository: PokemonRepository
) {
    suspend operator fun invoke(pokemonId: String): Flow<Result<Pokemon>> {
        return repository.getPokemonDetailById(pokemonId)
    }
}