package dev.skybit.pokedex.main.pokemon.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.skybit.pokedex.main.pokemon.domain.model.PokemonBaseStats
import dev.skybit.pokedex.main.pokemon.domain.model.PokemonDetails

@Entity(tableName = "pokemon_details")
data class PokemonDetailsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val types: List<String>,
    val weightKg: Double,
    val heightCm: Int,
    @Embedded val pokemonBasicStats: PokemonBaseStats
) {
    fun toDomain(): PokemonDetails {
        return PokemonDetails(
            id = id,
            name = name,
            types = types,
            weightKg = weightKg,
            heightCm = heightCm,
            pokemonBasicStats = pokemonBasicStats
        )
    }
}
