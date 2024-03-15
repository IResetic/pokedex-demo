package dev.skybit.pokedex.main.typedetails.domain.usecases

import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.typedetails.domain.model.PokemonBasicInfo

interface GetPokemonsBasicInfoByTypeId {
    suspend operator fun invoke(typeId: Int): Resource<List<PokemonBasicInfo>>
}
