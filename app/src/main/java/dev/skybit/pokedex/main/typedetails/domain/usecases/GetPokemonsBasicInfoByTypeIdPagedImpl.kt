package dev.skybit.pokedex.main.typedetails.domain.usecases

import androidx.paging.PagingData
import dev.skybit.pokedex.main.typedetails.domain.model.PokemonBasicInfo
import dev.skybit.pokedex.main.typedetails.domain.repository.PokemonTypeDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonsBasicInfoByTypeIdPagedImpl @Inject constructor(
    private val pokemonTypeDetailsRepository: PokemonTypeDetailsRepository
) : GetPokemonsBasicInfoByTypeIdPaged {

    override fun invoke(typeId: Int): Flow<PagingData<PokemonBasicInfo>> {
        return pokemonTypeDetailsRepository.getPokemonTypeDetailsPaged(typeId)
    }
}
