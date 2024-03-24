package dev.skybit.pokedex.main.typedetails.domain.usecases

import androidx.paging.PagingData
import dev.skybit.pokedex.main.typedetails.domain.model.PokemonBasicInfo
import kotlinx.coroutines.flow.Flow

interface GetPokemonsBasicInfoByTypeIdPaged {
    operator fun invoke(typeId: Int): Flow<PagingData<PokemonBasicInfo>>
}
