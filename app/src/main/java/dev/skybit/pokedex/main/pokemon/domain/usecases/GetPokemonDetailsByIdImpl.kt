package dev.skybit.pokedex.main.pokemon.domain.usecases

import dev.skybit.pokedex.main.pokemon.domain.model.PokemonDetails
import dev.skybit.pokedex.main.pokemon.domain.repository.PokemonDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonDetailsByIdImpl @Inject constructor(
    private val pokemonDetailsRepository: PokemonDetailsRepository
) : GetPokemonDetailsById {
    override fun invoke(pokemonId: Int): Flow<PokemonDetails?> {
        return pokemonDetailsRepository.getPokemonDetailsById(pokemonId)
    }
}
