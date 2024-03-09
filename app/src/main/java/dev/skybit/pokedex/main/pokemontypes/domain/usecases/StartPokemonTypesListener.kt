package dev.skybit.pokedex.main.pokemontypes.domain.usecases

import dev.skybit.pokedex.main.core.domain.model.PokemonType
import kotlinx.coroutines.flow.Flow

interface StartPokemonTypesListener {
    suspend operator fun invoke(): Flow<List<PokemonType>>
}
