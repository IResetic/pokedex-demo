package dev.skybit.pokedex.main.core.domain.usecases

import dev.skybit.pokedex.main.core.domain.model.PokemonType

interface GetPokemonTypeBasicInfo {
    suspend operator fun invoke(pokemonTypeId: Int): PokemonType
}
