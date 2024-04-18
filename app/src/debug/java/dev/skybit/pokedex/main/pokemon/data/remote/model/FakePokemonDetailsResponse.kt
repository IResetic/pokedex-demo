package dev.skybit.pokedex.main.pokemon.data.remote.model

import dev.skybit.pokedex.main.core.data.remote.model.ResultDto

val fakePokemonTypeDtoOne = PokemonTypeDto(
    slot = 1,
    type = ResultDto(
        name = "fire",
        url = "https://pokeapi.co/api/v2/type/10/"
    )
)

val fakePokemonTypeDtoTwo = PokemonTypeDto(
    slot = 2,
    type = ResultDto(
        name = "flying",
        url = "https://pokeapi.co/api/v2/type/3/"
    )
)

val fakePokemonBasicStatsDtoOne = PokemonBasicStatsDto(
    baseStat = 45,
    effort = 0,
    stat = ResultDto(
        name = "hp",
        url = "https://pokeapi.co/api/v2/stat/1/"
    )
)

val fakePokemonBasicStatsDtoTwo = PokemonBasicStatsDto(
    baseStat = 60,
    effort = 0,
    stat = ResultDto(
        name = "attack",
        url = "https://pokeapi.co/api/v2/stat/2/"
    )
)

val fakePokemonDetailsResponseOne = PokemonDetailsResponse(
    id = 1,
    name = "bulbasaur",
    weight = 69,
    height = 7,
    baseStats = listOf(fakePokemonBasicStatsDtoOne),
    types = listOf(fakePokemonTypeDtoOne)
)

val fakePokemonDetailsResponseTwo = PokemonDetailsResponse(
    id = 2,
    name = "ivysaur",
    weight = 130,
    height = 10,
    baseStats = listOf(fakePokemonBasicStatsDtoTwo),
    types = listOf(fakePokemonTypeDtoTwo)
)
