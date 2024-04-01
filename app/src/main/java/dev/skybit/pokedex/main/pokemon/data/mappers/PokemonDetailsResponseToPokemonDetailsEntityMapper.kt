package dev.skybit.pokedex.main.pokemon.data.mappers

import dev.skybit.pokedex.main.pokemon.data.local.model.PokemonDetailsEntity
import dev.skybit.pokedex.main.pokemon.data.remote.model.PokemonBasicStatsDto
import dev.skybit.pokedex.main.pokemon.data.remote.model.PokemonDetailsResponse
import dev.skybit.pokedex.main.pokemon.domain.model.PokemonBaseStats
import java.math.RoundingMode
import javax.inject.Inject

class PokemonDetailsResponseToPokemonDetailsEntityMapper @Inject constructor() {
    operator fun invoke(response: PokemonDetailsResponse): PokemonDetailsEntity {
        return PokemonDetailsEntity(
            id = response.id,
            name = response.name ?: "",
            heightCm = decimetresToCentimetres(response.height),
            weightKg = hectogramsToKilograms(response.weight),
            types = response.types?.map { it.type.name } ?: emptyList(),
            pokemonBasicStats = pokemonBasicStatsDtoToPokemonBasicStats(response.baseStats)
        )
    }

    private fun hectogramsToKilograms(hectograms: Int?): Double {
        return (hectograms?.let { it * 0.1 } ?: 0.0).toBigDecimal().setScale(1, RoundingMode.UP).toDouble()
    }

    private fun decimetresToCentimetres(decimetres: Int?): Int {
        return decimetres?.let { it * 10 } ?: 0
    }

    private fun pokemonBasicStatsDtoToPokemonBasicStats(basicStats: List<PokemonBasicStatsDto>?): PokemonBaseStats {
        return basicStats?.associate { it.stat.name to it.baseStat }?.let { statsMap ->
            PokemonBaseStats(
                hp = statsMap[HP_STAT] ?: DEFAULT_STAT_VALUE,
                attack = statsMap[ATTACK_STAT] ?: DEFAULT_STAT_VALUE,
                defense = statsMap[DEFENSE_STAT] ?: DEFAULT_STAT_VALUE,
                specialAttack = statsMap[SPECIAL_ATTACK_STAT] ?: DEFAULT_STAT_VALUE,
                specialDefense = statsMap[SPECIAL_DEFENSE_STAT] ?: DEFAULT_STAT_VALUE,
                speed = statsMap[SPEED_STAT] ?: DEFAULT_STAT_VALUE
            )
        } ?: PokemonBaseStats()
    }

    companion object {
        const val DEFAULT_STAT_VALUE = 0
        const val HP_STAT = "hp"
        const val ATTACK_STAT = "attack"
        const val DEFENSE_STAT = "defense"
        const val SPECIAL_ATTACK_STAT = "special-attack"
        const val SPECIAL_DEFENSE_STAT = "special-defense"
        const val SPEED_STAT = "speed"
    }
}
