package dev.skybit.pokedex.main.pokemontypes.domain.usecases

import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.typedetails.domain.model.PokemonBasicInfo
import dev.skybit.pokedex.main.typedetails.domain.usecases.GetPokemonsBasicInfoByTypeId

class FakeGetPokemonsBasicInfoByTypeId : GetPokemonsBasicInfoByTypeId {
    var fakePokemonBasicInfoList: Resource<List<PokemonBasicInfo>> = Resource.Success(emptyList())

    override suspend fun invoke(typeId: Int): Resource<List<PokemonBasicInfo>> {
        return fakePokemonBasicInfoList
    }
}
