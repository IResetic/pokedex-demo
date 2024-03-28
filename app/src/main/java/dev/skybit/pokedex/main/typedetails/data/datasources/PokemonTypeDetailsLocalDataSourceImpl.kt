package dev.skybit.pokedex.main.typedetails.data.datasources

import androidx.paging.PagingSource
import dev.skybit.pokedex.main.typedetails.data.local.dao.PokemonBasicInfoDao
import dev.skybit.pokedex.main.typedetails.data.local.model.PokemonBasicInfoEntity
import javax.inject.Inject

class PokemonTypeDetailsLocalDataSourceImpl @Inject constructor(
    private val pokemonBasicInfoDao: PokemonBasicInfoDao
) : PokemonTypeDetailsLocalDataSource {

    override suspend fun insertOrUpdatePokemonBasicInfo(pokemonBasicInfo: List<PokemonBasicInfoEntity>) {
        return pokemonBasicInfoDao.insertOrUpdatePokemonBasicInfo(pokemonBasicInfo)
    }

    override suspend fun getPokemonBasicInfoById(pokemonId: Int): PokemonBasicInfoEntity {
        return pokemonBasicInfoDao.getPokemonBasicInfoById(pokemonId)
    }

    override fun getPokemonBasicInfoByTypePaged(typeId: Int): PagingSource<Int, PokemonBasicInfoEntity> {
        return pokemonBasicInfoDao.getPokemonBasicInfoByTypePaged(typeId)
    }
}
