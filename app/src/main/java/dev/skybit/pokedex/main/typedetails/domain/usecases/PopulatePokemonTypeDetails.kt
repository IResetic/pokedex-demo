package dev.skybit.pokedex.main.typedetails.domain.usecases

import dev.skybit.pokedex.main.core.utils.Resource

interface PopulatePokemonTypeDetails {
    suspend operator fun invoke(typeId: Int): Resource<Unit>
}
