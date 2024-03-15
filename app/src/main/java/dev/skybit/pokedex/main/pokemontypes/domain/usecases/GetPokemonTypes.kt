package dev.skybit.pokedex.main.pokemontypes.domain.usecases

import dev.skybit.pokedex.main.core.domain.model.PokemonType
import dev.skybit.pokedex.main.core.utils.Resource

interface GetPokemonTypes {
    suspend operator fun invoke(): Resource<List<PokemonType>>
}
