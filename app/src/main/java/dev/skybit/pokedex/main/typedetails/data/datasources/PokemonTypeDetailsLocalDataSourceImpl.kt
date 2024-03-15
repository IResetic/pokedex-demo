package dev.skybit.pokedex.main.typedetails.data.datasources

import dev.skybit.pokedex.main.typedetails.data.local.dao.PokemonBasicInfoDao
import dev.skybit.pokedex.main.typedetails.data.local.model.PokemonBasicInfoEntity
import javax.inject.Inject

class PokemonTypeDetailsLocalDataSourceImpl @Inject constructor(
    private val pokemonBasicInfoDao: PokemonBasicInfoDao
): PokemonTypeDetailsLocalDataSource {
    override suspend fun insertOrUpdatePokemonBasicInfo(pokemonBasicInfo: List<PokemonBasicInfoEntity>) {
        return pokemonBasicInfoDao.insertOrUpdatePokemonBasicInfo(pokemonBasicInfo)
    }
}
