package dev.skybit.pokedex.main.typedetails.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.skybit.pokedex.main.typedetails.domain.model.PokemonBasicInfo
import kotlinx.coroutines.flow.Flow

class FakeGetPokemonsBasicInfoByTypeIdPaged : GetPokemonsBasicInfoByTypeIdPaged {
    var fakePokemonBasicInfoData: List<PokemonBasicInfo> = emptyList()

    override fun invoke(typeId: Int): Flow<PagingData<PokemonBasicInfo>> {
        var fakePagingSource = object : PagingSource<Int, PokemonBasicInfo>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonBasicInfo> {
                return LoadResult.Page(
                    data = fakePokemonBasicInfoData,
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
