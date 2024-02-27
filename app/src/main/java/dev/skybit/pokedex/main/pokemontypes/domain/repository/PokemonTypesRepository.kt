package dev.skybit.pokedex.main.pokemontypes.domain.repository

import dev.skybit.pokedex.main.pokemontypes.domain.model.PokemonType

interface PokemonTypesRepository {
    suspend fun getPokemonTypes(): List<PokemonType>

    suspend fun getPokemonTypeDetails(typeId: Int): PokemonType
}
