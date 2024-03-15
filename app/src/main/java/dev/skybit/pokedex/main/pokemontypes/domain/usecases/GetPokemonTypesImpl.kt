package dev.skybit.pokedex.main.pokemontypes.domain.usecases

import dev.skybit.pokedex.main.core.domain.model.PokemonType
import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.pokemontypes.domain.repository.PokemonTypesRepository
import java.util.concurrent.CancellationException
import javax.inject.Inject

class GetPokemonTypesImpl @Inject constructor(
    private val pokemonTypesRepository: PokemonTypesRepository
) : GetPokemonTypes {
    override suspend fun invoke(): Resource<List<PokemonType>> {
        return try {
            pokemonTypesRepository.populatePokemonTypes(0)
            val pokemonType = pokemonTypesRepository.getPokemonTypes()

            Resource.Success(pokemonType)
        } catch (e: Exception) {
            if(e is CancellationException) {
                throw e
            } else {
                val pokemonTypes = pokemonTypesRepository.getPokemonTypes()
                Resource.Error(e.message ?: "An error occurred", data = pokemonTypes)
            }
        }
    }
}
