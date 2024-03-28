package dev.skybit.pokedex.main.typedetails.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.skybit.pokedex.main.typedetails.domain.model.PokemonBasicInfo
import kotlinx.coroutines.flow.Flow

class FakePokemonTypeDetailsRepository : PokemonTypeDetailsRepository {
    var shouldThrowException: Boolean = false
    var exceptionMessage: String? = "An error occurred"
    val fakePokemonBasicInfoList = mutableListOf<PokemonBasicInfo>()

    override suspend fun populatePokemonTypeDetails(pokemonTypeId: Int) {
        if (shouldThrowException) {
            throw Exception(exceptionMessage)
        }
    }

    override suspend fun getPokemonBasicInfoById(pokemonId: Int): PokemonBasicInfo {
        return fakePokemonBasicInfoList.first { it.id == pokemonId }
    }

    override fun getPokemonTypeDetailsPaged(pokemonTypeId: Int): Flow<PagingData<PokemonBasicInfo>> {
        val fakePagingSource = object : PagingSource<Int, PokemonBasicInfo>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonBasicInfo> {
                return LoadResult.Page(
                    data = fakePokemonBasicInfoList,
                    prevKey = null,
                    nextKey = null
                )
            }

            override fun getRefreshKey(state: PagingState<Int, PokemonBasicInfo>): Int? {
                // Return null because there's only one page in this fake implementation
                return null
            }
        }

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { fakePagingSource }
        ).flow
    }
}
