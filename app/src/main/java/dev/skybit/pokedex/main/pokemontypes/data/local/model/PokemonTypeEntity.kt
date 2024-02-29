package dev.skybit.pokedex.main.pokemontypes.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_types")
data class PokemonTypeEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val name: String,
    val imageUrl: String
)
