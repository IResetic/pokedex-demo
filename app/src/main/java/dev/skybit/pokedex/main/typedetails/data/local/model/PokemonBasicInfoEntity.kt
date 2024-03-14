package dev.skybit.pokedex.main.typedetails.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_basic_info")
data class PokemonBasicInfoEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val typeId: Int,
    val typeName: String
)
