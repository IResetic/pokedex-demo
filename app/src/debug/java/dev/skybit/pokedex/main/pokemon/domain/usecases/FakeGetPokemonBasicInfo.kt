package dev.skybit.pokedex.main.pokemon.domain.usecases

import dev.skybit.pokedex.main.typedetails.domain.model.PokemonBasicInfo
import dev.skybit.pokedex.main.typedetails.domain.model.fakePokemonBasicInfoBulbasaur

class FakeGetPokemonBasicInfo : GetPokemonBasicInfo {
    var fakePokemonBasicInfo = fakePokemonBasicInfoBulbasaur

    override suspend fun invoke(pokemonId: Int): PokemonBasicInfo {
        return fakePokemonBasicInfo
    }
}
