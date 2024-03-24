package dev.skybit.pokedex.main.pokemontypes.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.skybit.pokedex.main.core.domain.model.PokemonType

@Entity(tableName = "pokemon_types")
data class PokemonTypeEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String
) {
    fun toDomain(): PokemonType {
        return PokemonType(id, name)
    }
}
