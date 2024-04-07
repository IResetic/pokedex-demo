package dev.skybit.pokedex.main.pokemon.domain.usecases

import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.pokemon.domain.model.PokemonDetails
import dev.skybit.pokedex.main.pokemon.domain.repository.PokemonDetailsRepository
import javax.inject.Inject

class GetPokemonDetailsImpl @Inject constructor(
    private val pokemonDetailsRepository: PokemonDetailsRepository,
    private val populatePokemonDetails: PopulatePokemonDetails
) : GetPokemonDetails {
    override suspend fun invoke(pokemonId: Int): Resource<PokemonDetails> {
        val result = populatePokemonDetails(pokemonId)
        val pokemons = pokemonDetailsRepository.getPokemonDetails(pokemonId)

        return if (result is Resource.Error || pokemons == null) {
            Resource.Error(result.message ?: "An error occurred", data = pokemons)
        } else {
            Resource.Success(pokemons)
        }
    }
}
