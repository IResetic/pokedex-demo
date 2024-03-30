package dev.skybit.pokedex.main.pokemon.data.datasources

import dev.skybit.pokedex.main.pokemon.data.local.model.PokemonDetailsEntity
import kotlinx.coroutines.flow.Flow

interface PokemonDetailsLocalDataSource {
    suspend fun insertOrUpdatePokemonDetails(pokemonDetails: PokemonDetailsEntity)

    fun getPokemonDetails(pokemonId: Int): Flow<PokemonDetailsEntity?>
}
