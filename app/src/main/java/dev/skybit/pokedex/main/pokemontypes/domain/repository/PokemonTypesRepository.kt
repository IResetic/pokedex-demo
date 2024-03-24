package dev.skybit.pokedex.main.pokemontypes.domain.repository

import dev.skybit.pokedex.main.core.domain.model.PokemonType

interface PokemonTypesRepository {
    suspend fun getPokemonTypes(): List<PokemonType>

    suspend fun getPokemonTypeBasicIInfoById(pokemonTypeId: Int): PokemonType

    suspend fun populatePokemonTypes(offset: Int)
}
