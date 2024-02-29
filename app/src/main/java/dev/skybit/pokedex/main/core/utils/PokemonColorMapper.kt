package dev.skybit.pokedex.main.core.utils

import androidx.compose.ui.graphics.Color
import dev.skybit.pokedex.main.core.presentation.style.theme.TypeBug
import dev.skybit.pokedex.main.core.presentation.style.theme.TypeDark
import dev.skybit.pokedex.main.core.presentation.style.theme.TypeDragon
import dev.skybit.pokedex.main.core.presentation.style.theme.TypeElectric
import dev.skybit.pokedex.main.core.presentation.style.theme.TypeFairy
import dev.skybit.pokedex.main.core.presentation.style.theme.TypeFighting
import dev.skybit.pokedex.main.core.presentation.style.theme.TypeFire
import dev.skybit.pokedex.main.core.presentation.style.theme.TypeFlying
import dev.skybit.pokedex.main.core.presentation.style.theme.TypeGhost
import dev.skybit.pokedex.main.core.presentation.style.theme.TypeGrass
import dev.skybit.pokedex.main.core.presentation.style.theme.TypeGround
import dev.skybit.pokedex.main.core.presentation.style.theme.TypeIce
import dev.skybit.pokedex.main.core.presentation.style.theme.TypeNormal
import dev.skybit.pokedex.main.core.presentation.style.theme.TypePoison
import dev.skybit.pokedex.main.core.presentation.style.theme.TypePsychic
import dev.skybit.pokedex.main.core.presentation.style.theme.TypeRock
import dev.skybit.pokedex.main.core.presentation.style.theme.TypeSteel
import dev.skybit.pokedex.main.core.presentation.style.theme.TypeWater
import dev.skybit.pokedex.main.pokemontypes.domain.model.PokemonType
import java.util.Locale

fun parseTypeToColor(type: PokemonType): Color {
    return when (type.name.lowercase(Locale.ROOT)) {
        POKEMON_TYPE_NORMAL -> TypeNormal
        POKEMON_TYPE_FIRE -> TypeFire
        POKEMON_TYPE_WATER -> TypeWater
        POKEMON_TYPE_ELECTRIC -> TypeElectric
        POKEMON_TYPE_GRASS -> TypeGrass
        POKEMON_TYPE_ICE -> TypeIce
        POKEMON_TYPE_FIGHTING -> TypeFighting
        POKEMON_TYPE_POISON -> TypePoison
        POKEMON_TYPE_GROUND -> TypeGround
        POKEMON_TYPE_FLYING -> TypeFlying
        POKEMON_TYPE_PSYCHIC -> TypePsychic
        POKEMON_TYPE_BUG -> TypeBug
        POKEMON_TYPE_ROCK -> TypeRock
        POKEMON_TYPE_GHOST -> TypeGhost
        POKEMON_TYPE_DRAGON -> TypeDragon
        POKEMON_TYPE_DARK -> TypeDark
        POKEMON_TYPE_STEEL -> TypeSteel
        POKEMON_TYPE_FAIRY -> TypeFairy
        else -> Color.Black
    }
}
