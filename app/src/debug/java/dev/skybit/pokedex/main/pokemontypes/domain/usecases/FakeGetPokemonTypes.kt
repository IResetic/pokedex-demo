package dev.skybit.pokedex.main.pokemontypes.domain.usecases

import dev.skybit.pokedex.main.pokemontypes.domain.model.PokemonType
import dev.skybit.pokedex.main.pokemontypes.domain.model.fakePokemonTypeFire
import dev.skybit.pokedex.main.pokemontypes.domain.model.fakePokemonTypeGrass

class FakeGetPokemonTypes : GetPokemonTypes {
    var fakePokemonTypes: List<PokemonType> = mutableListOf(fakePokemonTypeGrass, fakePokemonTypeFire)
    override suspend fun invoke(): List<PokemonType> {
        return fakePokemonTypes
    }
}
