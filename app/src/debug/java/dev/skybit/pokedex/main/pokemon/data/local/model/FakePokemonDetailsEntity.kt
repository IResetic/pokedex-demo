package dev.skybit.pokedex.main.pokemon.data.local.model

import dev.skybit.pokedex.main.pokemon.domain.model.fakePokemonBasicStatsOne
import dev.skybit.pokedex.main.pokemon.domain.model.fakePokemonBasicStatsTwo

val fakePokemonDetailsEntityOne = PokemonDetailsEntity(
    id = 1,
    name = "bulbasaur",
    types = listOf("grass", "poison"),
    heightCm = 7,
    weightKg = 69.0,
    pokemonBasicStats = fakePokemonBasicStatsOne
)

val fakePokemonDetailsEntityTwo = PokemonDetailsEntity(
    id = 2,
    name = "ivysaur",
    types = listOf("grass", "poison"),
    heightCm = 10,
    weightKg = 130.0,
    pokemonBasicStats = fakePokemonBasicStatsTwo
)
