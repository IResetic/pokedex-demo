package dev.skybit.pokedex.main.pokemon.domain.usecases

import dev.skybit.pokedex.main.typedetails.domain.model.PokemonBasicInfo
import dev.skybit.pokedex.main.typedetails.domain.repository.PokemonTypeDetailsRepository
import javax.inject.Inject

class GetPokemonBasicInfoImpl @Inject constructor(
    private val pokemonTypeDetailsRepository: PokemonTypeDetailsRepository
) : GetPokemonBasicInfo {
    override suspend operator fun invoke(pokemonId: Int): PokemonBasicInfo {
        return pokemonTypeDetailsRepository.getPokemonBasicInfoById(pokemonId)
    }
}
