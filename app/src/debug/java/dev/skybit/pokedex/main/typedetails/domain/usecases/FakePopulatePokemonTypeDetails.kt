package dev.skybit.pokedex.main.typedetails.domain.usecases

import dev.skybit.pokedex.main.core.utils.Resource

class FakePopulatePokemonTypeDetails : PopulatePokemonTypeDetails {
    var fakePokemonBasicInfoList: Resource<Unit> = Resource.Success(Unit)

    override suspend fun invoke(typeId: Int): Resource<Unit> {
        return fakePokemonBasicInfoList
    }
}
