package dev.skybit.pokedex.main.pokemontypes.domain.usecases

import dev.skybit.pokedex.main.pokemontypes.domain.model.PokemonType
import dev.skybit.pokedex.main.pokemontypes.domain.model.fakePokemonTypeFire
import dev.skybit.pokedex.main.pokemontypes.domain.model.fakePokemonTypeGrass
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeStartPokemonTypesListener : StartPokemonTypesListener {
    var fakePokemonTypes: List<PokemonType> = mutableListOf(fakePokemonTypeGrass, fakePokemonTypeFire)
    private val pokemonTypesFlow = MutableSharedFlow<List<PokemonType>>(replay = 1)

    override suspend fun invoke(): Flow<List<PokemonType>> {
        return pokemonTypesFlow
    }

    suspend fun emitPokemonTypes(pokemonTypes: List<PokemonType>) {
        pokemonTypesFlow.emit(pokemonTypes)
    }
}
