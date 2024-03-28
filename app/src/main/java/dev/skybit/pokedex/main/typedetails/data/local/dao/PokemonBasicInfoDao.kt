package dev.skybit.pokedex.main.typedetails.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import dev.skybit.pokedex.main.typedetails.data.local.model.PokemonBasicInfoEntity

@Dao
interface PokemonBasicInfoDao {
    @Upsert
    suspend fun insertOrUpdatePokemonBasicInfo(pokemonBasicInfo: List<PokemonBasicInfoEntity>)

    @Query("SELECT * FROM pokemon_basic_info WHERE pokemonTypeId = :pokemonTypeId")
    suspend fun getPokemonBasicInfoByType(pokemonTypeId: Int): List<PokemonBasicInfoEntity>

    @Query("SELECT * FROM pokemon_basic_info WHERE id = :pokemonId")
    suspend fun getPokemonBasicInfoById(pokemonId: Int): PokemonBasicInfoEntity

    @Query("SELECT * FROM pokemon_basic_info WHERE pokemonTypeId = :pokemonTypeId")
    fun getPokemonBasicInfoByTypePaged(pokemonTypeId: Int): PagingSource<Int, PokemonBasicInfoEntity>
}
