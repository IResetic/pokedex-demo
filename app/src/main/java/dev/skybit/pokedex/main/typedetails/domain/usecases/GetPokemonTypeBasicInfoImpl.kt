package dev.skybit.pokedex.main.typedetails.domain.usecases

import dev.skybit.pokedex.main.core.domain.model.PokemonType
import dev.skybit.pokedex.main.pokemontypes.domain.repository.PokemonTypesRepository
import javax.inject.Inject

class GetPokemonTypeBasicInfoImpl @Inject constructor(
    private val pokemonTypesRepository: PokemonTypesRepository
) : GetPokemonTypeBasicInfo {

    override suspend operator fun invoke(pokemonTypeId: Int): PokemonType {
        return pokemonTypesRepository.getPokemonTypeBasicIInfoById(pokemonTypeId)
    }
}
