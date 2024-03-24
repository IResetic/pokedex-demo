package dev.skybit.pokedex.main.typedetails.data.datasources

import androidx.paging.PagingSource
import dev.skybit.pokedex.main.typedetails.data.local.model.PokemonBasicInfoEntity

interface PokemonTypeDetailsLocalDataSource {
    suspend fun insertOrUpdatePokemonBasicInfo(pokemonBasicInfo: List<PokemonBasicInfoEntity>)

    suspend fun getPokemonBasicInfoByType(typeId: Int): List<PokemonBasicInfoEntity>

    fun getPokemonBasicInfoByTypePaged(typeId: Int): PagingSource<Int, PokemonBasicInfoEntity>
}
