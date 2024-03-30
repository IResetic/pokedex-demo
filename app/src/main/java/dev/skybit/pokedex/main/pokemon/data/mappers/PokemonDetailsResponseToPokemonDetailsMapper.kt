package dev.skybit.pokedex.main.pokemon.data.mappers

import dev.skybit.pokedex.main.pokemon.data.remote.model.PokemonBasicStatsDto
import dev.skybit.pokedex.main.pokemon.data.remote.model.PokemonDetailsResponse
import dev.skybit.pokedex.main.pokemon.domain.model.PokemonBaseStats
import dev.skybit.pokedex.main.pokemon.domain.model.PokemonDetails
import javax.inject.Inject

class PokemonDetailsResponseToPokemonDetailsMapper @Inject constructor() {
    operator fun invoke(response: PokemonDetailsResponse): PokemonDetails {
        return PokemonDetails(
            id = response.id,
            name = response.name ?: "",
            types = response.types?.map { it.type.name } ?: emptyList(),
            heightCm = decimetresToCentimetres(response.height),
            weightKg = hectogramsToKilograms(response.weight),
            pokemonBasicStats = pokemonBasicStatsDtoToPokemonBasicStats(response.baseStats)
        )
    }

    private fun hectogramsToKilograms(hectograms: Int?): Double {
        return hectograms?.let { it * 0.1 } ?: 0.0
    }

    private fun decimetresToCentimetres(decimetres: Int?): Int {
        return decimetres?.let { it * 10 } ?: 0
    }

    private fun pokemonBasicStatsDtoToPokemonBasicStats(basicStats: List<PokemonBasicStatsDto>?): PokemonBaseStats {
        val pokemonStats = PokemonBaseStats()

        basicStats?.let { stats ->
            pokemonStats.copy(
                hp = stats.find { it.stat.name == "hp" }?.baseStat ?: 0,
                attack = stats.find { it.stat.name == "attack" }?.baseStat ?: 0,
                defense = stats.find { it.stat.name == "defense" }?.baseStat ?: 0,
                specialAttack = stats.find { it.stat.name == "special-attack" }?.baseStat ?: 0,
                specialDefense = stats.find { it.stat.name == "special-defense" }?.baseStat ?: 0,
                speed = stats.find { it.stat.name == "speed" }?.baseStat ?: 0
            )
        }

        return pokemonStats
    }
}
