package dev.skybit.pokedex.main.pokemontypes.domain.usecases

import dev.skybit.pokedex.main.pokemontypes.domain.model.PokemonType

interface GetPokemonTypes {
    suspend operator fun invoke(): List<PokemonType>
}
