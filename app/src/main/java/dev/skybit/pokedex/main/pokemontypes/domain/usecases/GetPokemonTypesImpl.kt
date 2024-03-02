package dev.skybit.pokedex.main.pokemontypes.domain.usecases

import dev.skybit.pokedex.main.pokemontypes.domain.model.PokemonType
import dev.skybit.pokedex.main.pokemontypes.domain.repository.PokemonTypesRepository
import javax.inject.Inject

class GetPokemonTypesImpl @Inject constructor(
    private val pokemonTypesRepository: PokemonTypesRepository
) : GetPokemonTypes {
    override suspend operator fun invoke(): List<PokemonType> {
        return pokemonTypesRepository.getPokemonTypes()
    }
}
