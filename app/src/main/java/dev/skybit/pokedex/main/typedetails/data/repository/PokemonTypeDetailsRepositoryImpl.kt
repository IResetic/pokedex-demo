package dev.skybit.pokedex.main.typedetails.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import dev.skybit.pokedex.main.core.utils.POKEMON_BASIC_INFO_INITIAL_LOAD_SIZE
import dev.skybit.pokedex.main.core.utils.POKEMON_BASIC_INFO_PAGE_SIZE
import dev.skybit.pokedex.main.typedetails.data.datasources.PokemonTypeDetailsLocalDataSource
import dev.skybit.pokedex.main.typedetails.data.datasources.PokemonTypeDetailsRemoteDataSource
import dev.skybit.pokedex.main.typedetails.data.local.model.PokemonBasicInfoEntity
import dev.skybit.pokedex.main.typedetails.data.mappers.ResultDtoToPokemonBasicInfoEntityMapper
import dev.skybit.pokedex.main.typedetails.data.remote.model.PokemonTypeDetailsResponse
import dev.skybit.pokedex.main.typedetails.domain.model.PokemonBasicInfo
import dev.skybit.pokedex.main.typedetails.domain.repository.PokemonTypeDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonTypeDetailsRepositoryImpl @Inject constructor(
    private val pokemonTypeDetailsRemoteDataSource: PokemonTypeDetailsRemoteDataSource,
    private val pokemonTypeDetailsLocalDataSource: PokemonTypeDetailsLocalDataSource,
    private val resultDtoToPokemonBasicInfoEntityMapper: ResultDtoToPokemonBasicInfoEntityMapper
) : PokemonTypeDetailsRepository {

    override suspend fun populatePokemonTypeDetails(pokemonTypeId: Int) {
        val result = pokemonTypeDetailsRemoteDataSource.getPokemonTypeDetails(pokemonTypeId)

        if (result.isSuccessful) {
            val body = result.body()
            body?.let { response ->
                val pokemons = extractPokemonBasicInfoList(response)
                pokemonTypeDetailsLocalDataSource.insertOrUpdatePokemonBasicInfo(
                    pokemons
                )
            }
        } else {
            throw result.errorBody()?.let {
                val error = it.string()
                Exception(error)
            } ?: Exception("An error occurred")
        }
    }

    override fun getPokemonTypeDetailsPaged(pokemonTypeId: Int): Flow<PagingData<PokemonBasicInfo>> {
        return Pager(
            PagingConfig(
                pageSize = POKEMON_BASIC_INFO_PAGE_SIZE,
                initialLoadSize = POKEMON_BASIC_INFO_INITIAL_LOAD_SIZE,
                enablePlaceholders = false
            )
        ) {
            pokemonTypeDetailsLocalDataSource.getPokemonBasicInfoByTypePaged(pokemonTypeId)
        }.flow.map { pagingData ->
            pagingData.map { pokemon ->
                pokemon.toDomain()
            }
        }
    }

    private fun extractPokemonBasicInfoList(response: PokemonTypeDetailsResponse): List<PokemonBasicInfoEntity> {
        val pokemonTypeId = response.id
        val pokemonTypeName = response.name ?: "Unknown Type"
        val pokemons = response.pokemons?.map { pokemons ->
            resultDtoToPokemonBasicInfoEntityMapper(
                resultDto = pokemons.pokemon,
                pokemonTypeId = pokemonTypeId,
                pokemonTypeName = pokemonTypeName
            )
        }

        return pokemons ?: emptyList()
    }
}
