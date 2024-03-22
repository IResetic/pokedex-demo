package dev.skybit.pokedex.main.typedetails.data.datasources

import dev.skybit.pokedex.main.typedetails.data.local.model.PokemonBasicInfoEntity

class FakePokemonTypeDetailsLocalDataSource : PokemonTypeDetailsLocalDataSource {
    var fakePokemonBasicInfoStorage = mutableListOf<PokemonBasicInfoEntity>()

    override suspend fun insertOrUpdatePokemonBasicInfo(pokemonBasicInfo: List<PokemonBasicInfoEntity>) {
        fakePokemonBasicInfoStorage.removeAll { existingEntity ->
            pokemonBasicInfo.any { newEntity ->
                newEntity.id == existingEntity.id
            }
        }

        fakePokemonBasicInfoStorage.addAll(pokemonBasicInfo)
    }

    override suspend fun getPokemonBasicInfoByType(typeId: Int): List<PokemonBasicInfoEntity> {
        return fakePokemonBasicInfoStorage.filter { it.pokemonTypeId == typeId }
    }
}
