package dev.skybit.pokedex.main.pokemon.presentation.model

import dev.skybit.pokedex.main.core.utils.IMAGE_BASE_URL
import dev.skybit.pokedex.main.core.utils.POKEMON_IMG_FORMAT
import dev.skybit.pokedex.main.pokemon.data.mappers.PokemonDetailsResponseToPokemonDetailsEntityMapper.Companion.ATTACK_STAT
import dev.skybit.pokedex.main.pokemon.data.mappers.PokemonDetailsResponseToPokemonDetailsEntityMapper.Companion.DEFENSE_STAT
import dev.skybit.pokedex.main.pokemon.data.mappers.PokemonDetailsResponseToPokemonDetailsEntityMapper.Companion.HP_STAT
import dev.skybit.pokedex.main.pokemon.data.mappers.PokemonDetailsResponseToPokemonDetailsEntityMapper.Companion.SPECIAL_ATTACK_STAT
import dev.skybit.pokedex.main.pokemon.data.mappers.PokemonDetailsResponseToPokemonDetailsEntityMapper.Companion.SPECIAL_DEFENSE_STAT
import dev.skybit.pokedex.main.pokemon.data.mappers.PokemonDetailsResponseToPokemonDetailsEntityMapper.Companion.SPEED_STAT
import dev.skybit.pokedex.main.pokemon.domain.model.PokemonBaseStats
import dev.skybit.pokedex.main.pokemon.domain.model.PokemonDetails

data class PokemonDetailsUi(
    val id: Int,
    val name: String,
    val types: List<String>,
    val weightKg: Double,
    val heightCm: Int,
    val imageUrl: String,
    val stats: List<PokemonStat>
) {
    companion object {
        private fun pokemonBasicStatsToList(pokemonBasicStat: PokemonBaseStats): List<PokemonStat> {
            return listOf(
                PokemonStat(HP_STAT, pokemonBasicStat.hp),
                PokemonStat(ATTACK_STAT, pokemonBasicStat.attack),
                PokemonStat(DEFENSE_STAT, pokemonBasicStat.defense),
                PokemonStat(SPECIAL_ATTACK_STAT, pokemonBasicStat.specialAttack),
                PokemonStat(SPECIAL_DEFENSE_STAT, pokemonBasicStat.specialDefense),
                PokemonStat(SPEED_STAT, pokemonBasicStat.speed)
            )
        }

        fun fromDomain(pokemonDetails: PokemonDetails): PokemonDetailsUi {
            return PokemonDetailsUi(
                id = pokemonDetails.id,
                name = pokemonDetails.name,
                types = pokemonDetails.types,
                weightKg = pokemonDetails.weightKg,
                heightCm = pokemonDetails.heightCm,
                imageUrl = "$IMAGE_BASE_URL${pokemonDetails.id}$POKEMON_IMG_FORMAT",
                stats = pokemonBasicStatsToList(pokemonDetails.pokemonBasicStats)
            )
        }
    }
}

data class PokemonStat(
    val statName: String,
    val statValue: Int
)
