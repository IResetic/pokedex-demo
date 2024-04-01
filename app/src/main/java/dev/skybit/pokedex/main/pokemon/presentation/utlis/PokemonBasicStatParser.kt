package dev.skybit.pokedex.main.pokemon.presentation.utlis

import androidx.compose.ui.graphics.Color
import dev.skybit.pokedex.main.core.presentation.style.theme.AtkColor
import dev.skybit.pokedex.main.core.presentation.style.theme.DefColor
import dev.skybit.pokedex.main.core.presentation.style.theme.HPColor
import dev.skybit.pokedex.main.core.presentation.style.theme.SpAtkColor
import dev.skybit.pokedex.main.core.presentation.style.theme.SpDefColor
import dev.skybit.pokedex.main.core.presentation.style.theme.SpdColor
import dev.skybit.pokedex.main.pokemon.data.mappers.PokemonDetailsResponseToPokemonDetailsEntityMapper.Companion.ATTACK_STAT
import dev.skybit.pokedex.main.pokemon.data.mappers.PokemonDetailsResponseToPokemonDetailsEntityMapper.Companion.DEFENSE_STAT
import dev.skybit.pokedex.main.pokemon.data.mappers.PokemonDetailsResponseToPokemonDetailsEntityMapper.Companion.HP_STAT
import dev.skybit.pokedex.main.pokemon.data.mappers.PokemonDetailsResponseToPokemonDetailsEntityMapper.Companion.SPECIAL_ATTACK_STAT
import dev.skybit.pokedex.main.pokemon.data.mappers.PokemonDetailsResponseToPokemonDetailsEntityMapper.Companion.SPECIAL_DEFENSE_STAT
import dev.skybit.pokedex.main.pokemon.data.mappers.PokemonDetailsResponseToPokemonDetailsEntityMapper.Companion.SPEED_STAT
import java.util.Locale

fun parseStatToColor(name: String): Color {
    return when (name.lowercase(Locale.ROOT)) {
        HP_STAT -> HPColor
        ATTACK_STAT -> AtkColor
        DEFENSE_STAT -> DefColor
        SPECIAL_ATTACK_STAT -> SpAtkColor
        SPECIAL_DEFENSE_STAT -> SpDefColor
        SPEED_STAT -> SpdColor
        else -> Color.White
    }
}
