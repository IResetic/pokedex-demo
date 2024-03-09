package dev.skybit.pokedex.main.pokemonslist.presentation.model

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import dev.skybit.pokedex.main.core.domain.model.PokemonType
import dev.skybit.pokedex.main.core.utils.parseTypeToColor

@Stable
data class PokemonsListBasicInfoUI(
    val title: String,
    val backgroundColor: Color
) {

    companion object {
        fun fromDomain(pokemonTypeBasicInfo: PokemonType) = PokemonsListBasicInfoUI(
            title = pokemonTypeBasicInfo.name,
            backgroundColor = parseTypeToColor(pokemonTypeBasicInfo)
        )
    }
}
