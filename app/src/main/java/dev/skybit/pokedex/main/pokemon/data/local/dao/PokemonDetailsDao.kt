package dev.skybit.pokedex.main.pokemon.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import dev.skybit.pokedex.main.pokemon.data.local.model.PokemonDetailsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDetailsDao {

    @Upsert
    suspend fun insertOrUpdatePokemonDetails(pokemonDetails: PokemonDetailsEntity)

    @Query("SELECT * FROM pokemon_details WHERE  id = :pokemonId")
    fun getPokemonDetails(pokemonId: Int): Flow<PokemonDetailsEntity?>
}
