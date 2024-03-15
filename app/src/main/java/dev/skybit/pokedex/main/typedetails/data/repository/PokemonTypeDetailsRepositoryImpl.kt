package dev.skybit.pokedex.main.typedetails.data.repository

import dev.skybit.pokedex.main.typedetails.data.datasources.PokemonTypeDetailsLocalDataSource
import dev.skybit.pokedex.main.typedetails.data.datasources.PokemonTypeDetailsRemoteDataSource
import dev.skybit.pokedex.main.typedetails.data.local.model.PokemonBasicInfoEntity
import dev.skybit.pokedex.main.typedetails.data.mappers.ResultDtoToPokemonBasicInfoEntity
import dev.skybit.pokedex.main.typedetails.data.remote.model.PokemonTypeDetailsResponse
import dev.skybit.pokedex.main.typedetails.domain.model.PokemonBasicInfo
import dev.skybit.pokedex.main.typedetails.domain.repository.PokemonTypeDetailsRepository
import javax.inject.Inject

class PokemonTypeDetailsRepositoryImpl @Inject constructor(
    private val pokemonTypeDetailsRemoteDataSource: PokemonTypeDetailsRemoteDataSource,
    private val pokemonTypeDetailsLocalDataSource: PokemonTypeDetailsLocalDataSource,
    private val resultDtoToPokemonBasicInfoEntity: ResultDtoToPokemonBasicInfoEntity
) : PokemonTypeDetailsRepository {

    override suspend fun getPokemonTypeDetails(pokemonTypeId: Int): List<PokemonBasicInfo> {
        return pokemonTypeDetailsLocalDataSource.getPokemonBasicInfoByType(pokemonTypeId).map {
            it.toDomain()
        }
    }

    override suspend fun fetchPokemonTypeDetails(pokemonTypeId: Int) {
        val result = pokemonTypeDetailsRemoteDataSource.getPokemonTypeDetails(pokemonTypeId)

        if (result.isSuccessful) {
            val body = result.body()
            body?.let { response ->
                val pokemons = extractPokemonBasicInfoList(response)
                pokemonTypeDetailsLocalDataSource.insertOrUpdatePokemonBasicInfo(
                    pokemons ?: emptyList()
                )
            }
        } else {
            throw result.errorBody()?.let {
                val error = it.string()
                Exception(error)
            } ?: Exception("An error occurred")
        }
    }

    private fun extractPokemonBasicInfoList(response: PokemonTypeDetailsResponse): List<PokemonBasicInfoEntity> {
        val pokemonTypeId = response.id
        val pokemonTypeName = response.name ?: "Unknown Type"
        val pokemons = response.pokemons?.map { pokemons ->
            resultDtoToPokemonBasicInfoEntity(
                resultDto = pokemons.pokemon,
                pokemonTypeId = pokemonTypeId,
                pokemonTypeName = pokemonTypeName
            )
        }

        return pokemons ?: emptyList()
    }
}
