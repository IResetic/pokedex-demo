package dev.skybit.pokedex.main.pokemon.domain.usecases

import dev.skybit.pokedex.main.typedetails.domain.model.PokemonBasicInfo

interface GetPokemonBasicInfo {
    suspend operator fun invoke(pokemonId: Int): PokemonBasicInfo
}
