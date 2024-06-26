package dev.skybit.pokedex.main.pokemontypes.presentation.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import dev.skybit.pokedex.main.core.domain.model.PokemonType
import dev.skybit.pokedex.main.core.utils.parseTypeToColor
import dev.skybit.pokedex.main.pokemontypes.utils.parseTypeNameToImage

@Immutable
data class PokemonTypeUI(
    val id: Int,
    val name: String,
    val color: Color,
    val icon: Int
) {
    companion object {
        fun fromDomain(type: PokemonType): PokemonTypeUI {
            return PokemonTypeUI(
                id = type.id,
                name = type.name,
                color = parseTypeToColor(type),
                icon = parseTypeNameToImage(type.name)
            )
        }

        fun fromDomainList(types: List<PokemonType>): List<PokemonTypeUI> {
            return types.map { fromDomain(it) }
        }
    }
}
