package dev.skybit.pokedex.main.pokemontypes.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import dev.skybit.pokedex.main.pokemontypes.data.local.model.PokemonTypeEntity

@Dao
interface PokemonTypeDao {
    @Upsert
    suspend fun insertOrUpdatePokemonTypes(pokemonTypes: List<PokemonTypeEntity>)

    @Query("SELECT * FROM pokemon_types")
    fun getAllPokemonTypes(): List<PokemonTypeEntity>
}
