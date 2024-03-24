package dev.skybit.pokedex.main.typedetails.presentation.model

import dev.skybit.pokedex.main.core.utils.IMAGE_BASE_URL
import dev.skybit.pokedex.main.core.utils.POKEMON_IMG_FORMAT
import dev.skybit.pokedex.main.typedetails.domain.model.PokemonBasicInfo

data class PokemonBasicInfoUi(
    val id: Int,
    val name: String,
    val imageUrl: String
) {
    companion object {
        fun fromDomain(pokemonBasicInfo: PokemonBasicInfo) = PokemonBasicInfoUi(
            id = pokemonBasicInfo.id,
            name = pokemonBasicInfo.name,
            imageUrl = "$IMAGE_BASE_URL${pokemonBasicInfo.id}$POKEMON_IMG_FORMAT"
        )

        fun fromDomainList(pokemons: List<PokemonBasicInfo>): List<PokemonBasicInfoUi> {
            return pokemons.map { fromDomain(it) }
        }
    }
}
