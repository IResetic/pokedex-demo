package dev.skybit.pokedex.main.pokemontypes.domain.usecases

import dev.skybit.pokedex.main.core.utils.Resource

interface PopulatePokemonTypes {
    suspend operator fun invoke(): Resource<Unit>
}
