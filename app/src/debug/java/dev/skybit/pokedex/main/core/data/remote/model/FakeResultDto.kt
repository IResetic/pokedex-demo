package dev.skybit.pokedex.main.core.data.remote.model

import dev.skybit.pokedex.main.ELECTRIC_POKEMON_TYPE_ID
import dev.skybit.pokedex.main.FIRE_POKEMON_TYPE_ID
import dev.skybit.pokedex.main.GRASS_POKEMON_TYPE_ID
import dev.skybit.pokedex.main.WATER_POKEMON_TYPE_ID
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_ELECTRIC
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_FIRE
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_GRASS
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_WATER

val fakeResultDtoGrass = ResultDto(
    name = POKEMON_TYPE_GRASS,
    url = "https://pokeapi.co/api/v2/type/$GRASS_POKEMON_TYPE_ID/"
)

val fakeResultDtoFire = ResultDto(
    name = POKEMON_TYPE_FIRE,
    url = "https://pokeapi.co/api/v2/type/$FIRE_POKEMON_TYPE_ID/"
)

val fakeResultDtoWater = ResultDto(
    name = POKEMON_TYPE_WATER,
    url = "https://pokeapi.co/api/v2/type/$WATER_POKEMON_TYPE_ID/"
)

val fakeResultDtoElectric = ResultDto(
    name = POKEMON_TYPE_ELECTRIC,
    url = "https://pokeapi.co/api/v2/type/$ELECTRIC_POKEMON_TYPE_ID/"
)
