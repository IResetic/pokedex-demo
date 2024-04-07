package dev.skybit.pokedex.main.pokemon.domain.model

val fakePokemonBasicStatsOne = PokemonBaseStats(
    hp = 45,
    attack = 49,
    defense = 49,
    specialAttack = 65,
    specialDefense = 65,
    speed = 45
)

val fakePokemonBasicStatsTwo = PokemonBaseStats(
    hp = 60,
    attack = 62,
    defense = 63,
    specialAttack = 80,
    specialDefense = 80,
    speed = 60
)

val fakePokemonDetailsOne = PokemonDetails(
    id = 1,
    name = "Bulbasaur",
    types = listOf("Grass", "Poison"),
    weightKg = 6.9,
    heightCm = 7,
    pokemonBasicStats = fakePokemonBasicStatsOne
)

val fakePokemonDetailsTwo = PokemonDetails(
    id = 2,
    name = "Ivysaur",
    types = listOf("Grass", "Poison"),
    weightKg = 13.0,
    heightCm = 10,
    pokemonBasicStats = fakePokemonBasicStatsTwo
)
