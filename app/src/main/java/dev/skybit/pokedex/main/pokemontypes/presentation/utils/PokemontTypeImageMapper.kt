package dev.skybit.pokedex.main.pokemontypes.presentation.utils

import dev.skybit.pokedex.R
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_BUG
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_DARK
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_DRAGON
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_ELECTRIC
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_FAIRY
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_FIGHTING
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_FIRE
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_FLYING
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_GHOST
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_GRASS
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_GROUND
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_ICE
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_NORMAL
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_POISON
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_PSYCHIC
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_ROCK
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_STEEL
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_WATER
import java.util.Locale

fun parseTypeNameToImage(name: String): Int {
    return when (name.lowercase(Locale.ROOT)) {
        POKEMON_TYPE_NORMAL -> R.drawable.ic_normal
        POKEMON_TYPE_FIRE -> R.drawable.ic_fire
        POKEMON_TYPE_WATER -> R.drawable.ic_water
        POKEMON_TYPE_ELECTRIC -> R.drawable.ic_electric
        POKEMON_TYPE_GRASS -> R.drawable.ic_grass
        POKEMON_TYPE_ICE -> R.drawable.ic_ice
        POKEMON_TYPE_FIGHTING -> R.drawable.ic_fighting
        POKEMON_TYPE_POISON -> R.drawable.ic_poison
        POKEMON_TYPE_GROUND -> R.drawable.ic_ground
        POKEMON_TYPE_FLYING -> R.drawable.ic_flying
        POKEMON_TYPE_PSYCHIC -> R.drawable.ic_psychic
        POKEMON_TYPE_BUG -> R.drawable.ic_bug
        POKEMON_TYPE_ROCK -> R.drawable.ic_rock
        POKEMON_TYPE_GHOST -> R.drawable.ic_ghost
        POKEMON_TYPE_DRAGON -> R.drawable.ic_dragon
        POKEMON_TYPE_DARK -> R.drawable.ic_dark
        POKEMON_TYPE_STEEL -> R.drawable.ic_steel
        POKEMON_TYPE_FAIRY -> R.drawable.ic_fairy
        else -> R.drawable.ic_poke_ball
    }
}
