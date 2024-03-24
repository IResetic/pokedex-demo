package dev.skybit.pokedex.main.typedetails.domain.usecases

import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.typedetails.domain.repository.PokemonTypeDetailsRepository
import javax.inject.Inject

class PopulatePokemonTypeDetailsImpl @Inject constructor(
    private val pokemonTypeDetailsRepository: PokemonTypeDetailsRepository
) : PopulatePokemonTypeDetails {
    override suspend fun invoke(typeId: Int): Resource<Unit> {
        return try {
            pokemonTypeDetailsRepository.populatePokemonTypeDetails(typeId)
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(message = e.message ?: "An error occurred")
        }
    }
}
