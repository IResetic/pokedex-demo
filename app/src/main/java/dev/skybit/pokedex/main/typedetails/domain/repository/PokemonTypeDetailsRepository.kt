package dev.skybit.pokedex.main.typedetails.domain.repository

import androidx.paging.PagingData
import dev.skybit.pokedex.main.typedetails.domain.model.PokemonBasicInfo
import kotlinx.coroutines.flow.Flow

interface PokemonTypeDetailsRepository {
    suspend fun populatePokemonTypeDetails(pokemonTypeId: Int)

    suspend fun getPokemonBasicInfoById(pokemonId: Int): PokemonBasicInfo

    fun getPokemonTypeDetailsPaged(pokemonTypeId: Int): Flow<PagingData<PokemonBasicInfo>>
}
