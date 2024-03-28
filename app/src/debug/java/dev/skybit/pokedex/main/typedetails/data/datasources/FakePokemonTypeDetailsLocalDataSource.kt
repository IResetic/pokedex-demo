package dev.skybit.pokedex.main.typedetails.data.datasources

import androidx.paging.PagingSource
import androidx.paging.PagingState
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

    override suspend fun getPokemonBasicInfoById(pokemonId: Int): PokemonBasicInfoEntity {
        return fakePokemonBasicInfoStorage.first { it.id == pokemonId }
    }

    override fun getPokemonBasicInfoByTypePaged(typeId: Int): PagingSource<Int, PokemonBasicInfoEntity> {
        return object : PagingSource<Int, PokemonBasicInfoEntity>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonBasicInfoEntity> {
                val data = fakePokemonBasicInfoStorage.filter { it.pokemonTypeId == typeId }
                return LoadResult.Page(
                    data = data,
                    prevKey = null,
                    nextKey = null
                )
            }

            override fun getRefreshKey(state: PagingState<Int, PokemonBasicInfoEntity>): Int? {
                return null
            }
        }
    }
}
