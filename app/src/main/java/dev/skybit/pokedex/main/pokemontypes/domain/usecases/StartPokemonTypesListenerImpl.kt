package dev.skybit.pokedex.main.pokemontypes.domain.usecases

import dev.skybit.pokedex.main.pokemontypes.domain.model.PokemonType
import dev.skybit.pokedex.main.pokemontypes.domain.repository.PokemonTypesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StartPokemonTypesListenerImpl @Inject constructor(
    private val pokemonTypesRepository: PokemonTypesRepository
) : StartPokemonTypesListener {
    override suspend fun invoke(): Flow<List<PokemonType>> {
        return pokemonTypesRepository.getPokemonTypesFlow()
    }
}
