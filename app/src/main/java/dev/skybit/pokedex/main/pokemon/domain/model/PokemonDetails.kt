package dev.skybit.pokedex.main.pokemon.domain.model

data class PokemonDetails(
    val id: Int,
    val name: String,
    val types: List<String>,
    val weightKg: Int,
    val heightCm: Int,
    val baseStats: PokemonBaseStats
)

data class PokemonBaseStats(
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefense: Int,
    val speed: Int
)
