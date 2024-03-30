package dev.skybit.pokedex.main.pokemon.domain.usecases

import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.pokemon.domain.repository.PokemonDetailsRepository
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class PopulatePokemonDetailsImpl @Inject constructor(
    private val pokemonDetailsRepository: PokemonDetailsRepository
) : PopulatePokemonDetails {
    override suspend fun invoke(pokemonId: Int): Resource<Unit> {
        return try {
            pokemonDetailsRepository.populatePokemonDetails(pokemonId)
            Resource.Success(Unit)
        } catch (e: Exception) {
            if (e is CancellationException) {
                throw e
            }
            Resource.Error(e.localizedMessage ?: "An error occurred")
        }
    }
}
