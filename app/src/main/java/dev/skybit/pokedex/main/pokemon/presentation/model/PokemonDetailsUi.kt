package dev.skybit.pokedex.main.pokemon.presentation.model

import dev.skybit.pokedex.main.core.utils.IMAGE_BASE_URL
import dev.skybit.pokedex.main.core.utils.POKEMON_IMG_FORMAT
import dev.skybit.pokedex.main.pokemon.domain.model.PokemonBaseStats
import dev.skybit.pokedex.main.pokemon.domain.model.PokemonDetails

data class PokemonDetailsUi(
    val id: Int,
    val name: String,
    val types: List<String>,
    val weightKg: Double,
    val heightCm: Int,
    val imageUrl: String,
    val pokemonBasicStats: PokemonBaseStats
) {
    companion object {
        fun fromDomain(pokemonDetails: PokemonDetails): PokemonDetailsUi {
            return PokemonDetailsUi(
                id = pokemonDetails.id,
                name = pokemonDetails.name,
                types = pokemonDetails.types,
                weightKg = pokemonDetails.weightKg,
                heightCm = pokemonDetails.heightCm,
                imageUrl = "$IMAGE_BASE_URL${pokemonDetails.id}$POKEMON_IMG_FORMAT",
                pokemonBasicStats = pokemonDetails.pokemonBasicStats
            )
        }
    }
}
