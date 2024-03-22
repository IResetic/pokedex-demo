package dev.skybit.pokedex.main.typedetails.domain.repository

import dev.skybit.pokedex.main.typedetails.domain.model.PokemonBasicInfo

class FakePokemonTypeDetailsRepository : PokemonTypeDetailsRepository {
    var shouldThrowException: Boolean = false
    var exceptionMessage: String? = "An error occurred"
    val fakePokemonBasicInfoList = mutableListOf<PokemonBasicInfo>()

    override suspend fun getPokemonTypeDetails(pokemonTypeId: Int): List<PokemonBasicInfo> {
        return fakePokemonBasicInfoList
    }

    override suspend fun populatePokemonTypeDetails(pokemonTypeId: Int) {
        if (shouldThrowException) {
            throw Exception(exceptionMessage)
        }
    }
}
