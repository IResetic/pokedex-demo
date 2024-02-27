package dev.skybit.pokedex.main.pokemontypes.domain.model

data class TypeDetails(
    val id: Int,
    val name: String,
    val imageName: String,
    val numberOfPokemons: Int,
    val introductionGeneration: String,
    val pokemons: List<TypePokemon>
)
