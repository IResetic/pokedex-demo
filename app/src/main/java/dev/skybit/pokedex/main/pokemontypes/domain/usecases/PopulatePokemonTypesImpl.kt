package dev.skybit.pokedex.main.pokemontypes.domain.usecases

import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.pokemontypes.domain.repository.PokemonTypesRepository
import javax.inject.Inject

class PopulatePokemonTypesImpl @Inject constructor(
    private val pokemonTypesRepository: PokemonTypesRepository
) : PopulatePokemonTypes {

    override suspend fun invoke(): Resource<Unit> {
        return pokemonTypesRepository.populatePokemonTypes()
    }
}
