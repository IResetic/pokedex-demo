package dev.skybit.pokedex.main.pokemontypes.data.datasources

import dev.skybit.pokedex.main.pokemontypes.data.local.model.PokemonTypeEntity

interface PokemonTypesLocalDataSource {
    suspend fun insertPokemonType(types: List<PokemonTypeEntity>)
}
