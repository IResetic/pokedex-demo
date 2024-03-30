package dev.skybit.pokedex.main.pokemon.domain.model

data class PokemonDetails(
    val id: Int,
    val name: String,
    val types: List<String>,
    val weightKg: Double,
    val heightCm: Int,
    val pokemonBasicStats: PokemonBaseStats
)

data class PokemonBaseStats(
    val hp: Int = 0,
    val attack: Int = 0,
    val defense: Int = 0,
    val specialAttack: Int = 0,
    val specialDefense: Int = 0,
    val speed: Int = 0
)
