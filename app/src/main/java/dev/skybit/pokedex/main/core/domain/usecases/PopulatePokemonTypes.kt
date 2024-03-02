package dev.skybit.pokedex.main.core.domain.usecases

import dev.skybit.pokedex.main.core.utils.Resource

interface PopulatePokemonTypes {
    suspend operator fun invoke(): Resource<Unit>
}
