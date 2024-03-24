package dev.skybit.pokedex.main.typedetails.domain.model

data class PokemonTypeDetails(
    val id: Int,
    val name: String,
    val pokemons: List<PokemonBasicInfo>
)
