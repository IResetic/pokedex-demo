package dev.skybit.pokedex.main.typedetails.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.skybit.pokedex.main.typedetails.domain.model.PokemonBasicInfo

@Entity(tableName = "pokemon_basic_info")
data class PokemonBasicInfoEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val pokemonTypeId: Int,
    val pokemonTypeName: String
) {
    fun toDomain(): PokemonBasicInfo {
        return PokemonBasicInfo(
            id = id,
            name = name,
            pokemonTypeName = pokemonTypeName
        )
    }
}
