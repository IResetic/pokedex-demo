package dev.skybit.pokedex.main.pokemontypes.data.local

import androidx.room.Dao
import androidx.room.Query

@Dao
interface PokemonTypeDao {

    @Query("SELECT * FROM pokemon_types")
    suspend fun getAllPokemonTypes(): List<PokemonTypeEntity>
}
