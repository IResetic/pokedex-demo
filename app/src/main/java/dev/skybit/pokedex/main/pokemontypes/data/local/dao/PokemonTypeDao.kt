package dev.skybit.pokedex.main.pokemontypes.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import dev.skybit.pokedex.main.pokemontypes.data.local.model.PokemonTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonTypeDao {
    @Upsert
    suspend fun insertOrUpdatePokemonTypes(pokemonTypes: List<PokemonTypeEntity>)

    @Query("SELECT * FROM pokemon_types")
    fun getAllPokemonTypes(): List<PokemonTypeEntity>

    @Query("SELECT * FROM pokemon_types")
    fun getPokemonTypesFlow(): Flow<List<PokemonTypeEntity>>

    @Query("SELECT * FROM pokemon_types WHERE id = :id")
    fun getPokemonTypeById(id: Int): PokemonTypeEntity
}
