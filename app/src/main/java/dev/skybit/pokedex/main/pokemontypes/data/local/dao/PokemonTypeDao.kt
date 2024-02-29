package dev.skybit.pokedex.main.pokemontypes.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.skybit.pokedex.main.pokemontypes.data.local.model.PokemonTypeEntity

@Dao
interface PokemonTypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonTypes(pokemonTypes: List<PokemonTypeEntity>)

    @Query("SELECT * FROM pokemon_types")
    suspend fun getAllPokemonTypes(): List<PokemonTypeEntity>
}
