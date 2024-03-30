package dev.skybit.pokedex.main.pokemon.data.repository

import dev.skybit.pokedex.main.pokemon.data.datasources.PokemonDetailsLocalDataSource
import dev.skybit.pokedex.main.pokemon.data.datasources.PokemonDetailsRemoteDataSource
import dev.skybit.pokedex.main.pokemon.data.mappers.PokemonDetailsResponseToPokemonDetailsEntityMapper
import dev.skybit.pokedex.main.pokemon.data.mappers.PokemonDetailsResponseToPokemonDetailsMapper
import dev.skybit.pokedex.main.pokemon.domain.model.PokemonDetails
import dev.skybit.pokedex.main.pokemon.domain.repository.PokemonDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonDetailsRepositoryImpl @Inject constructor(
    private val pokemonDetailsRemoteDataSource: PokemonDetailsRemoteDataSource,
    private val pokemonDetailsLocalDataSource: PokemonDetailsLocalDataSource,
    private val pokemonDetailsResponseToPokemonDetailsMapper: PokemonDetailsResponseToPokemonDetailsMapper,
    private val pokemonDetailsResponseToPokemonDetailsEntityMapper: PokemonDetailsResponseToPokemonDetailsEntityMapper
) : PokemonDetailsRepository {

    override suspend fun getPokemonDetails(pokemonId: Int): PokemonDetails {
        val result = pokemonDetailsRemoteDataSource.getPokemonDetails(pokemonId)
        if (result.isSuccessful) {
            val body = result.body()

            return body?.let {
                pokemonDetailsResponseToPokemonDetailsMapper(it)
            } ?: throw Exception("An error occurred")
        } else {
            throw result.errorBody()?.let {
                val error = it.string()
                Exception(error)
            } ?: Exception("An error occurred")
        }
    }

    override suspend fun populatePokemonDetails(pokemonId: Int) {
        val result = pokemonDetailsRemoteDataSource.getPokemonDetails(pokemonId)
        if (result.isSuccessful) {
            val body = result.body()

            body?.let { pokemonDetailsResponse ->
                val pokemonDetailsEntity = pokemonDetailsResponseToPokemonDetailsEntityMapper(pokemonDetailsResponse)
                pokemonDetailsLocalDataSource.insertOrUpdatePokemonDetails(pokemonDetailsEntity)
            }
        } else {
            throw result.errorBody()?.let {
                val error = it.string()
                Exception(error)
            } ?: Exception("An error occurred")
        }
    }

    override fun getPokemonDetailsById(pokemonId: Int): Flow<PokemonDetails?> {
        return pokemonDetailsLocalDataSource.getPokemonDetails(pokemonId).map {
            it?.toDomain()
        }
    }
}