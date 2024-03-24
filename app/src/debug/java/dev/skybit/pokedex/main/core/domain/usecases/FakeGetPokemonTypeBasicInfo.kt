package dev.skybit.pokedex.main.core.domain.usecases

import dev.skybit.pokedex.main.core.domain.model.PokemonType
import dev.skybit.pokedex.main.core.domain.model.fakePokemonTypeFire

class FakeGetPokemonTypeBasicInfo : GetPokemonTypeBasicInfo {
    var fakePokemonType = fakePokemonTypeFire

    override suspend fun invoke(pokemonTypeId: Int): PokemonType {
        return fakePokemonType
    }
}
